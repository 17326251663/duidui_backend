package com.czxy.duidui.service;

import com.czxy.duidui.domain.ImageReview;
import com.czxy.duidui.vo.ImageReviewVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/20
 */
public interface ReviewService {

    PageInfo<ImageReview> getReviewWithImgId(ImageReviewVo imageReviewVo);

    void addReview(ImageReview imageReview);

    boolean likeReview(Integer rid, Integer uid);

    int lookUnreadReview(Integer uid);

    List<ImageReview> lookUnreadReviewMsg(Integer uid);
}
