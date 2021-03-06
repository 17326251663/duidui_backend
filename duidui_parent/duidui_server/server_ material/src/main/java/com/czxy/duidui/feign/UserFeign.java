package com.czxy.duidui.feign;

import com.czxy.duidui.domain.User;
import com.czxy.duidui.utils.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/2
 * @infos
 */
@FeignClient(value = "userservice",path = "/user")
public interface UserFeign {

    @GetMapping("/{uid}")
    public BaseResult<User> selectUserByUid(@PathVariable("uid") Integer uid);
}
