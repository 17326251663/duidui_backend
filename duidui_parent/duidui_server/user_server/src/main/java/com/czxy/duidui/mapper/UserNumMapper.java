package com.czxy.duidui.mapper;

import com.czxy.duidui.domain.UserNum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/11
 */
@org.apache.ibatis.annotations.Mapper
public interface UserNumMapper extends Mapper<UserNum> {

    @Select("select count(*) from  dd_user_attention_count where uid = #{uid} and fansId = #{fansId}")
    int isAttention(Integer uid, Integer fansId);


    @Delete("delete from dd_user_attention_count where uid = #{uid} and fansId = #{fansId}")
    int cancelAttention_count(Integer uid, Integer fansId);

    @Insert("insert into dd_user_attention_count values(null,#{uid},#{fansId},default)")
    int addAttention_count(Integer uid, Integer fansId);


    @Update("update dd_user_num set anum = anum + 1 where uid = #{uid}")
    int addAttentionNum(Integer uid);

    @Update("update dd_user_num set anum = anum - 1 where uid = #{uid}")
    int delAttentionNum(Integer uid);

    @Update("update dd_user_num set fnum = fnum + 1 where uid = #{uid}")
    int addFansNum(Integer uid);

    @Update("update dd_user_num set fnum = fnum - 1 where uid = #{uid}")
    int delFansNum(Integer uid);
}
