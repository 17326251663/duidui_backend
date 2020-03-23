package com.czxy.duidui.controller;

import com.czxy.duidui.domain.ImageReview;
import com.czxy.duidui.domain.User;
import com.czxy.duidui.service.ReviewService;
import com.czxy.duidui.utils.BaseResult;
import com.czxy.duidui.utils.JwtUtils;
import com.czxy.duidui.utils.Path;
import com.czxy.duidui.utils.RasUtils;
import com.czxy.duidui.vo.ImageReviewVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/20
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Resource
    ReviewService reviewService;

    @PostMapping
    public BaseResult<List<ImageReview>> getReviewWithImgId(@RequestBody ImageReviewVo imageReviewVo) {
        return BaseResult.ok("获取成功", reviewService.getReviewWithImgId(imageReviewVo));
    }

    @PostMapping("/add")
    public BaseResult<String> addReview(@RequestBody ImageReview imageReview, HttpServletRequest request){

        String usermsg = request.getHeader("token");

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            Integer uid = user.getUid();
            imageReview.setUid(uid);
            imageReview.setInfoTime(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date()));
            imageReview.setLookStatus("0");
            imageReview.setLikeNum(0);

            reviewService.addReview(imageReview);
            return BaseResult.ok("评论成功");

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return BaseResult.error("请先登录");
        }
    }

    @GetMapping("/likereview/{rid}")
    public BaseResult<String> likeReview(@PathVariable("rid") Integer rid,HttpServletRequest request){
        String usermsg = request.getHeader("token");

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            Integer uid = user.getUid();

            boolean key = reviewService.likeReview(rid,uid);
        if (key)
            return BaseResult.ok("点赞成功");
            return BaseResult.ok("取消点赞");
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return BaseResult.error("请先登录");
        }
    }

    @GetMapping("/lookUnreadReview")
    public BaseResult<Integer> lookUnreadReview(HttpServletRequest request){

        String usermsg = request.getHeader("token");


        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            Integer uid = user.getUid();

            int num = reviewService.lookUnreadReview(uid);

            return BaseResult.ok("200",num);

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return BaseResult.error("请先登录");
        }

    }

    @GetMapping("/lookUnreadReviewMsg")
    public BaseResult<List<ImageReview>> lookUnreadReviewMsg(HttpServletRequest request){

        String usermsg = request.getHeader("token");


        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            Integer uid = user.getUid();

          List<ImageReview> reviewList = reviewService.lookUnreadReviewMsg(uid);

            return  BaseResult.ok("查询成功",reviewList);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return BaseResult.error("请先登录");
        }

    }
}
