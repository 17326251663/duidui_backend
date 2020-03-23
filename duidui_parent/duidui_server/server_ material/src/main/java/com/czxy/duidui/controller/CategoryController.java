package com.czxy.duidui.controller;

import com.czxy.duidui.domain.MCategory;
import com.czxy.duidui.service.MCategoryService;
import com.czxy.duidui.utils.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/17
 * @infos
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    MCategoryService mCategoryService;

    @GetMapping
    public BaseResult<List<MCategory>> selectAll(){
        return BaseResult.ok("查询成功",mCategoryService.selectAll());
    }

    @GetMapping("/addCategoryWithUser/{uid}/{cids}")
    public BaseResult<String> addCategoryWithUser(@PathVariable("uid") Integer uid, @PathVariable("cids") Integer[] cids){
        mCategoryService.addCategoryWithUser(uid,cids);
        return BaseResult.ok("添加成功");
    }

    @GetMapping("/{cid}")
    public BaseResult<List<MCategory>> selectByCid(@PathVariable("cid") Integer cid){
        return BaseResult.ok("查询成功",mCategoryService.selectByPid(cid));
    }

}
