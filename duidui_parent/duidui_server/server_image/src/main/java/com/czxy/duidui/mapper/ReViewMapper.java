package com.czxy.duidui.mapper;

import com.czxy.duidui.domain.ImageReview;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/20
 */
@org.apache.ibatis.annotations.Mapper
public interface ReViewMapper extends Mapper<ImageReview> {

    @Delete("delete from dd_image_review_count where reviewId = #{rid} and uid = #{uid}")
    int delLikeReview_count(Integer rid, Integer uid);

    @Insert("insert into dd_image_review_count values(null,#{uid},#{rid},default)")
    int addLikeReview_count(Integer rid, Integer uid);

    @Update("update dd_image_review set likeNum = likeNum+1 where rid = #{rid}")
    int addLikeReview(Integer rid);

    @Update("update dd_image_review set likeNum = likeNum-1 where rid = #{rid}")
    int delLikeReview(Integer rid);
}
