package com.czxy.duidui.controller;

import com.czxy.duidui.domain.OftenLabel;
import com.czxy.duidui.service.OftenLabelService;
import com.czxy.duidui.utils.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/19
 */
@RestController
@RequestMapping("label")
public class OftenLabelController {

    @Resource
    OftenLabelService oftenLabelService;

    @GetMapping
    public BaseResult<List<OftenLabel>> selectOftenLabel(){
        return BaseResult.ok("查询成功",oftenLabelService.selectOftenLabel());
    }

}
