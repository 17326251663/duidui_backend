package com.czxy.duidui.controller;

import com.aliyuncs.exceptions.ClientException;
import com.czxy.duidui.domain.User;
import com.czxy.duidui.domain.UserNum;
import com.czxy.duidui.service.UserService;
import com.czxy.duidui.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/20
 * @infos
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${myredis.host}")
    private static String host;
    @Value("${myredis.port}")
    private static String port;


    @Resource
    UserService userService;


    /**
     * 获取验证码
     *
     * @param telephone 手机号
     * @return 发送结果
     */
    @GetMapping("/code/{telephone}")
    public BaseResult<String> getCode(@PathVariable("telephone") String telephone) {
        Jedis jedis = new Jedis();
        //验证是否为电话号码
        if (telephone.replaceAll("[^0-9]", "").length() != 11 || !telephone.startsWith("1")) {
            return BaseResult.error("手机号格式有误");
        }
        //判断手机号码是否已被使用
        int count = userService.selectByTelephone(telephone);
        if (count!=0)
            return BaseResult.error("手机号已被使用");

        //生成随机验证码
        String sendCode = null;
        //发送短信
        try {
            sendCode = NoteUtil.sendSms(telephone);
        } catch (ClientException e) {
            return BaseResult.error("网络延迟等原因,发送失败,请重新发送");
        }
        //将验证码存放到redis缓存中
        jedis.set("telephone:" + telephone, sendCode);
        //设置过期时间
        jedis.expire("telephone:" + telephone, 60 * 5);

        jedis.close();
        //返回验证码
        return BaseResult.ok("发送成功");
    }

    /**
     * 验证手机验证码
     *
     * @param code      验证码
     * @param telephone 手机号
     * @return 验证结果
     */
    @GetMapping("/verifycode/{telephone}/{code}")
    public BaseResult<String> verifyCode(@PathVariable("code") String code, @PathVariable("telephone") String telephone) {
        return verifyCodeStemp(code, telephone);
    }


    private BaseResult<String> verifyCodeStemp(String code, String telephone) {
        Jedis jedis = new Jedis();
        String s = jedis.get("telephone:" +telephone);
        jedis.close();
        if (s == null)
         return   BaseResult.error("请先获取验证码");
        if (code != null && !code.equals(s))
            return BaseResult.error("验证码验证失败");
        return BaseResult.ok("验证码验证成功", s);
    }

    @PostMapping("/registry")
    public BaseResult<String> registry(@RequestBody User user) {
        Jedis jedis = new Jedis();
        //再次验证验证码
        BaseResult<String> result = verifyCodeStemp(user.getCode(), user.getTelephone());
        //验证失败,提醒
        if (result.getCode() == 0)
            return BaseResult.error("注册失败,验证超时");

        //把数据信息存储到数据库
        userService.registry(user);
        //注册成功,删除验证码信息
        jedis.del("telephone:"+user.getTelephone());
        jedis.close();

        return BaseResult.ok("注册成功");
    }

    @PostMapping("/login")
    public BaseResult<String> login(@RequestBody User user) throws Exception {
        User login = userService.login(user);

        if (login==null)
            return BaseResult.error("登录失败,账号或密码有误");

        String token = JwtUtils.generateToken(login, 60*24*7, RasUtils.getPrivateKey(Path.privatePath));

        login.setPassword("");
        login.setEmail("");
        login.setTelephone("");


        return BaseResult.ok("登录成功",token).append("loginUser",login);
    }

    @PostMapping("/verifyUsername")
    public BaseResult<String> verifyUsername(@RequestBody User user){
       boolean key = userService.verifyUsername(user.getUsername());
       if (key)
           return BaseResult.ok("用户名可用");
           return BaseResult.error("用户名重复");
    }

    @GetMapping("/{uid}")
    public BaseResult<User> selectUserByUid(@PathVariable("uid") Integer uid){
        return BaseResult.ok("查询成功",userService.selectByUid(uid));
    }

    @GetMapping("/msg/{uid}")
    public BaseResult<User> userMessage(@PathVariable("uid") Integer uids) throws IOException {

            //获取用户
            User user = userService.selectByUid(uids);
            //获取用户id
            Integer uid = user.getUid();
            //获取用户关注与粉丝数量
            System.out.println(user);
            Jedis jedis = new Jedis();
            String s = jedis.get("userNum:" + uid);
            //声明变量
            UserNum userNum;
            //判断缓冲是否失效
            if (s!=null&&!s.equals(""))
            {
                //未失效，从缓存中获取
                userNum = new ObjectMapper().readValue(s, UserNum.class);
            }
            else
            {
                //失效，查询数据库并添加到缓存中
                userNum = userService.selectNumByUid(user.getUid());

                jedis.set("userNum:"+uid,new ObjectMapper().writeValueAsString(userNum));
                jedis.expire("userNum:"+uid,60*60);
                jedis.close();
            }


            user.setPassword(null);
            user.setUserNum(userNum);

            return BaseResult.ok("个人信息",user);

    }

    @PutMapping("/")
    public BaseResult<String> updateUserMsg(@RequestBody User newUser, HttpServletRequest request){

        String usermsg = request.getHeader("token");

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            newUser.setUid(user.getUid());
            newUser.setTelephone(user.getTelephone());
            newUser.setPassword(user.getPassword());

            userService.updateUserMsg(newUser);

            newUser.setPassword("");

            String s = JwtUtils.generateToken(newUser,60 * 24 * 7, RasUtils.getPrivateKey(Path.privatePath));

            return BaseResult.ok("更改成功",s).append("loginUser",newUser);


        } catch (Exception e) {
            return BaseResult.error("用户名重复");
        }
    }

    @GetMapping("/myCollectOrFans/{type}/{uid}/{pageNum}/{pageSize}")
    public BaseResult<PageInfo<User>> myCollectOrFans(@PathVariable("type") Integer type , @PathVariable("uid") Integer uid,@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize){

      PageInfo<User> userPageInfo = userService.myCollectOrFans(type,uid,pageNum,pageSize);

      return BaseResult.ok("查询成功",userPageInfo);
    }
}
