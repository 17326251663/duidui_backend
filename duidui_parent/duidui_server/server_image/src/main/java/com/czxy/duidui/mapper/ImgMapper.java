package com.czxy.duidui.mapper;

import com.czxy.duidui.domain.Img;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@org.apache.ibatis.annotations.Mapper
public interface ImgMapper extends Mapper<Img> {
    @Select("select * from dd_img order by date(uptime) desc , likenum desc,collectnum desc")
    List<Img> selectChoiceness();

    @Update("update dd_img set collectnum=collectnum+1 where iid = #{imgId}")
    void addCollect(Integer imgId);

    @Update("update dd_img set collectnum=collectnum-1 where iid = #{imgId}")
    void delCollect(Integer imgId);

    @Update("update dd_img set likenum=likenum-1 where iid = #{imgId}")
    void delLike(Integer imgId);

    @Update("update dd_img set likenum=likenum+1 where iid = #{imgId}")
    void addLike(Integer imgId);
}
