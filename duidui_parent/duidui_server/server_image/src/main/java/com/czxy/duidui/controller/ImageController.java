package com.czxy.duidui.controller;

import com.czxy.duidui.domain.Img;
import com.czxy.duidui.domain.User;
import com.czxy.duidui.service.ImageService;
import com.czxy.duidui.utils.BaseResult;
import com.czxy.duidui.utils.JwtUtils;
import com.czxy.duidui.utils.Path;
import com.czxy.duidui.utils.RasUtils;
import com.czxy.duidui.vo.ImageVo;
import com.czxy.duidui.vo.ResourceVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @Resource
    ImageService imageService;

    @PostMapping("/add")
    public BaseResult<String> addImage(@RequestBody Img img){
        imageService.addImage(img);
        return BaseResult.ok("添加图片成功");
    }

    @GetMapping("/choiceness/{pageNum}/{pageSize}")
    public BaseResult<PageInfo> selectChoiceness(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        return BaseResult.ok("查询成功",imageService.selectChoiceness(pageNum,pageSize));
    }

    @GetMapping("/state/{uid}")
    public BaseResult<Img> selectStateImg(@PathVariable("uid") Integer uid){

        List<Img> imgList =null;

        try {
             imgList = imageService.selectStateImg(uid);
            return BaseResult.ok("查询成功",imgList);
        } catch (Exception e) {
            imgList = new ArrayList<>();
            return BaseResult.ok("查询成功",imgList);
        }
    }

    @GetMapping("/{imgId}")
    public BaseResult<Img> selectImgByImgId(@PathVariable("imgId") Integer imgId){
        return  BaseResult.ok("查询成功",imageService.selectImgByImgId(imgId));
    }

    @PostMapping("/tag")
    public BaseResult<PageInfo<Img>> selectImageByTagName(@RequestBody ImageVo imageVo){

        PageInfo<Img> imgPageInfo = imageService.selectImageByTagName(imageVo);

        return BaseResult.ok("查询成功",imgPageInfo);
    }

    @PostMapping("/resource")
    public BaseResult<PageInfo>  selectSourceByCondition(@RequestBody ResourceVo resourceVo){
        PageInfo resourceInfo = imageService.selectSourceByCondition(resourceVo);
        return BaseResult.ok("查询成功",resourceInfo);
    }

    @GetMapping("/collect/{imgId}")
    public BaseResult<String> collectImg(@PathVariable("imgId") Integer imgId, HttpServletRequest request){

        String usermsg = request.getHeader("token");
        System.out.println(usermsg);

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            Integer uid = user.getUid();

          boolean result =  imageService.collectImg(uid,imgId);

            if (result)
              return   BaseResult.ok("收藏成功");
            else
                return  BaseResult.ok("取消收藏成功");

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return BaseResult.error("请先登录");
        }

    }

    @GetMapping("/like/{imgId}")
    public BaseResult<String> likeImg(@PathVariable("imgId") Integer imgId, HttpServletRequest request){

        String usermsg = request.getHeader("token");
        System.out.println(usermsg);

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            Integer uid = user.getUid();

            boolean result =  imageService.likeImg(uid,imgId);

            if (result)
                return   BaseResult.ok("点赞成功");
            else
                return  BaseResult.ok("取消点赞成功");

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return BaseResult.error("请先登录");
        }

    }

    @DeleteMapping("/{imgId}")
    public BaseResult<String> delImgListByImgIdWithUid(@PathVariable Integer imgId,HttpServletRequest request){

        String usermsg = request.getHeader("token");
        System.out.println(usermsg);

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            Integer uid = user.getUid();

            boolean key = imageService.delImgListByImgIdWithUid(imgId, uid);

            if (key)
                return BaseResult.ok("删除成功");
                return BaseResult.error("删除失败,请通过正规手段删除");

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return BaseResult.error("请先登录");
        }

    }

    @GetMapping("/collectList/{uid}")
    public BaseResult<List<Img>>  collectList(@PathVariable("uid") Integer uid){

      return BaseResult.ok("查询成功",imageService.collectList(uid));

    }

    @PutMapping("/updateTags")
    public BaseResult<String> updateTags(@RequestBody Img img,HttpServletRequest request){

        String usermsg = request.getHeader("token");
        System.out.println(usermsg);

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            Integer uid = user.getUid();

            imageService.updateTags(img,uid);

            return BaseResult.ok("修改成功");
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return BaseResult.error("请先登录");
        }

    }
}
