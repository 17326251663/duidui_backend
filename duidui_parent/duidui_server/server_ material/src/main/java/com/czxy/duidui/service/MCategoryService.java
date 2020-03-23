package com.czxy.duidui.service;

import com.czxy.duidui.domain.MCategory;

import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/17
 * @infos
 */
public interface MCategoryService {

    List<MCategory> selectAll();

    /**
     * 添加用户喜欢的素材分类
     * @param uid 用户id
     * @param cids 素材id数列
     */
    void addCategoryWithUser(Integer uid, Integer[] cids);

    List<MCategory> selectByPid(Integer cid);
}
