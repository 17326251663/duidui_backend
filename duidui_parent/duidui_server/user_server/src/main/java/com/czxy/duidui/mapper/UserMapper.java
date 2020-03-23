package com.czxy.duidui.mapper;

import com.czxy.duidui.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/20
 * @infos
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
    @Insert("insert into dd_user_createtime values(#{uid},#{ctime})")
    int createUserWithTime(Integer uid, Date ctime);

    @Insert("insert into dd_user_num values(null,#{uid},0,0)")
    int createUserWithNum(Integer uid);

    @Select("select * from dd_user t1 where t1.uid in (select t2.fansId from dd_user_attention_count t2 where t2.uid = #{uid})")
    List<User> myFans(Integer uid);

    @Select("select * from dd_user t1 where t1.uid in (select t2.uid from dd_user_attention_count t2 where t2.fansId = #{uid})")
    List<User> myCollect(Integer uid);

}
