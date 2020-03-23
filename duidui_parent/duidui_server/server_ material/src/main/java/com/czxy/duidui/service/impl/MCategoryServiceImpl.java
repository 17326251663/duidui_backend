package com.czxy.duidui.service.impl;

import com.czxy.duidui.domain.MCategory;
import com.czxy.duidui.mapper.MCategoryMapper;
import com.czxy.duidui.service.MCategoryService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/17
 * @infos
 */
@Service
@Transactional
@CacheConfig(cacheNames = {"mcategory"})
public class MCategoryServiceImpl implements MCategoryService {

    @Resource
    MCategoryMapper mCategoryMapper;

    private static MCategory mCategory;

    static {
        mCategory = new MCategory();
    }

    @Override
    @Cacheable
    public List<MCategory> selectAll() {
        mCategory.setParentId(0);
        return mCategoryMapper.select(mCategory);
    }

    @Override
    public void addCategoryWithUser(Integer uid, Integer[] cids) {
        for (Integer cid : cids) {
            mCategoryMapper.addCategoryWithUser(uid,cid);
        }
    }

    @Override
    public List<MCategory> selectByPid(Integer pid) {
        return mCategoryMapper.selectByPid(pid);
    }

}
