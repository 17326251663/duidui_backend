package com.czxy.duidui.service.impl;

import com.czxy.duidui.domain.OftenLabel;
import com.czxy.duidui.mapper.OftenLabelMapper;
import com.czxy.duidui.service.OftenLabelService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/19
 */
@Service
@Transactional
public class OftenLabelServiceImpl implements OftenLabelService {

    @Resource
    OftenLabelMapper oftenLabelMapper;

    @Override
    public List<OftenLabel> selectOftenLabel() {

        PageHelper.startPage(0,20);

        Example example = new Example(OftenLabel.class);
        example.orderBy("appearnum").desc();

        return oftenLabelMapper.selectByExample(example);
    }
}
