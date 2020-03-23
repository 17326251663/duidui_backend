package com.czxy.duidui.mapper;

import com.czxy.duidui.domain.OftenLabel;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/19
 */
@org.apache.ibatis.annotations.Mapper
public interface OftenLabelMapper extends Mapper<OftenLabel> {

    @Update("call add_label(#{aname})")
    void call_add_label(String aname);
}
