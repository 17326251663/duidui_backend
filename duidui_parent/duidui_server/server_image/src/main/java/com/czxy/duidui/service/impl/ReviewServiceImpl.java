package com.czxy.duidui.service.impl;

import com.czxy.duidui.domain.ImageReview;
import com.czxy.duidui.feign.UserFeign;
import com.czxy.duidui.mapper.ImgMapper;
import com.czxy.duidui.mapper.ReViewMapper;
import com.czxy.duidui.service.ReviewService;
import com.czxy.duidui.vo.ImageReviewVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/20
 */
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Resource
    ReViewMapper reViewMapper;

    @Resource
    UserFeign userFeign;

    @Resource
    ImgMapper imgMapper;

    @Override
    public PageInfo<ImageReview> getReviewWithImgId(ImageReviewVo imageReviewVo) {

        //开启分页
        PageHelper.startPage(imageReviewVo.getPageNum(), imageReviewVo.getPageSize());

        //开启条件查询
        Example example = new Example(ImageReview.class);

        example.createCriteria().andEqualTo("imgId", imageReviewVo.getImgId()).andEqualTo("parentId", imageReviewVo.getParentId());

        //排序方法
        if (imageReviewVo.getType() == 0)
            example.orderBy("likeNum").desc().orderBy("infoTime").desc();
        else
            example.orderBy("infoTime").desc().orderBy("likeNum").desc();

        //获取到分页下所有评论
        List<ImageReview> imageReviews = reViewMapper.selectByExample(example);


        //进行分页
        PageInfo<ImageReview> pageinfo = new PageInfo<>(imageReviews);

        //开启子评论查询
        List<ImageReview> list = pageinfo.getList();

        //查询用户信息
        for (int i = 0; i < list.size(); i++) {
            ImageReview imageReview = list.get(i);
            //查询评论所属用户信息
           imageReview.setUser( userFeign.selectUserByUid(imageReview.getUid()).getData());
        }

        //无限评论递归
      //  if (list.size() != 0) {
       //     geChildrentReviewWithImgId(imageReviewVo, list);
       // }

        //子评论再次查询

        return pageinfo;
    }

    @Override
    public void addReview(ImageReview imageReview) {
        reViewMapper.insert(imageReview);
    }

    @Override
    public boolean likeReview(Integer rid, Integer uid) {

        //先删除
        int i = reViewMapper.delLikeReview_count(rid, uid);
        //说明此次请求为取消点赞
        if (i!=0){
            reViewMapper.delLikeReview(rid);
            return false;
        }else {
            reViewMapper.addLikeReview_count(rid,uid);
            reViewMapper.addLikeReview(rid);
            return true;
        }

        //后添加

    }

    @Override
    public int lookUnreadReview(Integer uid) {

        Example example = new Example(ImageReview.class);
        example.createCriteria().andEqualTo("toUid",uid).andEqualTo("lookStatus",0).andNotEqualTo("uid",uid);

      return  reViewMapper.selectCountByExample(example);

    }

    @Override
    public List<ImageReview> lookUnreadReviewMsg(Integer uid) {

        PageHelper.startPage(1,50);

        Example example = new Example(ImageReview.class);
        example.createCriteria().andEqualTo("toUid",uid).andNotEqualTo("uid",uid);

        example.orderBy("lookStatus").desc().orderBy("infoTime").desc();

        List<ImageReview> reviewList = reViewMapper.selectByExample(example);

        PageInfo<ImageReview> imageReviewPageInfo = new PageInfo<>(reviewList);

        //填充图片集信息
        for (ImageReview imageReview : imageReviewPageInfo.getList()) {
            //填充歌曲信息
            imageReview.setImg(imgMapper.selectByPrimaryKey(imageReview.getImgId()));
            //填充用户信息
            imageReview.setUser(userFeign.selectUserByUid(imageReview.getUid()).getData());
        }

        //修改状态
        Example example1 = new Example(ImageReview.class);
        example1.createCriteria().andEqualTo("toUid",uid).andEqualTo("lookStatus",0);

        ImageReview imageReview = new ImageReview();
        imageReview.setLookStatus("1");

        reViewMapper.updateByExampleSelective(imageReview,example1);


        return imageReviewPageInfo.getList();
    }

    private void geChildrentReviewWithImgId(ImageReviewVo imageReviewVo, List<ImageReview> list) {

        ImageReviewVo vo = new ImageReviewVo();
        vo.setPageNum(1);
        vo.setPageSize(5);
        vo.setType(0);
        vo.setImgId(imageReviewVo.getImgId());

        for (int i = 0; i < list.size(); i++) {
            ImageReview imageReview = list.get(i);
            //更新父id
            vo.setParentId(imageReview.getParentId());
            //更新子评论
            imageReview.setList(getReviewWithImgId(vo).getList());
        }
    }
}
