package com.czxy.duidui.feign;

import com.czxy.duidui.utils.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/20
 * @infos
 */
@FeignClient(value = "MATERIALSERVICE",path = "/category")
public interface MaterialFeign {
    @GetMapping("/addCategoryWithUser/{uid}/{cids}")
    BaseResult addCategoryWithUser(@PathVariable("uid") Integer uid,@PathVariable("cids") Integer[] cids);
}