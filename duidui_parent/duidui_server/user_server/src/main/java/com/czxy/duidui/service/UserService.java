package com.czxy.duidui.service;

import com.czxy.duidui.domain.User;
import com.czxy.duidui.domain.UserNum;
import com.github.pagehelper.PageInfo;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/20
 * @infos
 */
public interface UserService {
    void registry(User user);

    User login(User user);

    boolean verifyUsername(String username);

    int selectByTelephone(String telephone);

    User selectByUid(Integer uid);

    UserNum selectNumByUid(Integer uid);

    void updateUserMsg(User user);

    PageInfo<User> myCollectOrFans(Integer type, Integer uid,Integer pageNum,Integer pageSize);
}
