package com.czxy.duidui.service.impl;

import com.czxy.duidui.domain.MenuList;
import com.czxy.duidui.mapper.MenuMapper;
import com.czxy.duidui.service.MenuService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@Service
@Transactional
@CacheConfig(cacheNames = {"menu_list"})
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuMapper menuMapper;



    @Override
    public List<MenuList> selectAll() {
        return menuMapper.selectAll();
    }

}
