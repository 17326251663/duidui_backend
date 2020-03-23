package com.czxy.duidui.mapper;

import com.czxy.duidui.domain.MCategory;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/17
 * @infos
 */
@org.apache.ibatis.annotations.Mapper
public interface MCategoryMapper extends Mapper<MCategory> {

    @Insert("insert into dd_category_user values(null,#{uid},#{cid})")
    void addCategoryWithUser(Integer uid, Integer cid);

    @Select("select * from dd_category where parent_id = #{cid}")
    @Results(value = {
            @Result(property = "cid",column = "cid"),
            @Result(property = "mList",column = "cid",many = @Many(select = "com.czxy.duidui.mapper.MCategoryMapper.selectByParentId"))
    })
    List<MCategory> selectByPid(Integer cid);

    @Select("select * from dd_category where parent_id = #{pid}")
    List<MCategory> selectByParentId(Integer pid);
}
