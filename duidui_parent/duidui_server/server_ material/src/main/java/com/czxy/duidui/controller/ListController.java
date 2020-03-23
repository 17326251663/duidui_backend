package com.czxy.duidui.controller;

import com.czxy.duidui.domain.MenuList;
import com.czxy.duidui.service.MenuService;
import com.czxy.duidui.utils.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@RestController
@RequestMapping("/list")
public class ListController {

    @Resource
    private MenuService menuService;

    @GetMapping
    public BaseResult<List<MenuList>> selectAllMenu(){
        return BaseResult.ok("查询成功",menuService.selectAll());
    }

}
