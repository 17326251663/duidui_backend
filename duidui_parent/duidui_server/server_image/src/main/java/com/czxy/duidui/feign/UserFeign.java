package com.czxy.duidui.feign;

import com.czxy.duidui.domain.User;
import com.czxy.duidui.utils.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/14
 */


@FeignClient(value = "userservice",path = "/user")
public interface UserFeign {

@GetMapping("/{uid}")
public BaseResult<User> selectUserByUid(@PathVariable("uid") Integer uid);

}

