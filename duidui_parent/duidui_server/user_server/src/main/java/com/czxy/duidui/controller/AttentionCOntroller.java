package com.czxy.duidui.controller;

import com.czxy.duidui.domain.User;
import com.czxy.duidui.service.AttentionService;
import com.czxy.duidui.utils.BaseResult;
import com.czxy.duidui.utils.JwtUtils;
import com.czxy.duidui.utils.Path;
import com.czxy.duidui.utils.RasUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/3/10
 */
@RestController
@RequestMapping("/attention")
public class AttentionCOntroller {


    @Resource
    AttentionService attentionService;

    @GetMapping("/isAttention/{uid}")
    public BaseResult<String>  isAttention(@PathVariable("uid") Integer uid1, HttpServletRequest request){

        String usermsg = request.getHeader("token");
        System.out.println(usermsg);

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

          boolean result = attentionService.isAttention(uid1,user.getUid());

          if (result)
              return BaseResult.ok("已关注");
              return BaseResult.ok("未关注");


        } catch (Exception e) {
            return BaseResult.error("请先登录");
        }

    }

    @GetMapping("/{uid}")
    public BaseResult<String> attentionUser(@PathVariable("uid") Integer uid,HttpServletRequest request){

        String usermsg = request.getHeader("token");
        System.out.println(usermsg);

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            //true代表此操作为关注,false为未关注
            boolean key = attentionService.attentionUser(uid,user.getUid());

            if (key)
                return BaseResult.ok("关注成功");
                return BaseResult.ok("取消关注");

        } catch (Exception e) {
            return BaseResult.error("请先登录");
        }
    }
}
