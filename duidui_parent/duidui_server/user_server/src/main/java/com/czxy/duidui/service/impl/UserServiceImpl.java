package com.czxy.duidui.service.impl;

import com.czxy.duidui.domain.User;
import com.czxy.duidui.domain.UserNum;
import com.czxy.duidui.feign.MaterialFeign;
import com.czxy.duidui.mapper.UserMapper;
import com.czxy.duidui.mapper.UserNumMapper;
import com.czxy.duidui.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/20
 * @infos
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    MaterialFeign materialFeign;
    @Resource
    UserNumMapper userNumMapper;

    @Override
    public void registry(User user) {

        userMapper.insert(user);

        userMapper.createUserWithNum(user.getUid());
        userMapper.createUserWithTime(user.getUid(),new Date());

    if (user.getLikeMCategory()!=null&&user.getLikeMCategory().length!=0)
        materialFeign.addCategoryWithUser(user.getUid(),user.getLikeMCategory());
    }

    @Override
    public User login(User user) {

        String telephone = user.getTelephone();
        String password = user.getPassword();

        if (telephone==null||telephone.length()!=11||password==null||password.equals(""))
            return null;
            return userMapper.selectOne(user);
    }

    @Override
    public boolean verifyUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);

        User user = userMapper.selectOneByExample(example);
        if (user==null)
        return true;
        return false;
    }

    @Override
    public int selectByTelephone(String telephone) {

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("telephone",telephone);

        return userMapper.selectCountByExample(example);
    }

    @Override
    public User selectByUid(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        User user1 = new User();
        user1.setUid(user.getUid());
        user1.setImageUrl(user.getImageUrl());
        user1.setUsername(user.getUsername());
        return user1;
    }

    @Override
    public UserNum selectNumByUid(Integer uid) {
        Example example = new Example(UserNum.class);
        example.createCriteria().andEqualTo("uid",uid);
        return userNumMapper.selectOneByExample(example);
    }

    @Override
    public void updateUserMsg(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public PageInfo<User> myCollectOrFans(Integer type, Integer uid , Integer pageNum,Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);

        List<User> userList = null;

        //关注
        if (type==0){

         userList  = userMapper.myCollect(uid);

        //粉丝
        }else {

         userList = userMapper.myFans(uid);

        }

        for (User user : userList) {
            user.setPassword("");
            UserNum userNum = new UserNum();
            userNum.setUid(user.getUid());
            user.setUserNum(userNumMapper.selectOne(userNum));
        }

        return new PageInfo<User>(userList);
    }
}
