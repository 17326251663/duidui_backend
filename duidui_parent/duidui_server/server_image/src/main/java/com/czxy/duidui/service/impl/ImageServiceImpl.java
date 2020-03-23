package com.czxy.duidui.service.impl;

import com.czxy.duidui.domain.Album;
import com.czxy.duidui.domain.Img;
import com.czxy.duidui.domain.ImgCollect;
import com.czxy.duidui.domain.ImgLike;
import com.czxy.duidui.mapper.AlbumMapper;
import com.czxy.duidui.mapper.ImgCollectMapper;
import com.czxy.duidui.mapper.ImgLikeMapper;
import com.czxy.duidui.mapper.ImgMapper;
import com.czxy.duidui.service.AlbumService;
import com.czxy.duidui.service.ImageService;
import com.czxy.duidui.vo.ImageVo;
import com.czxy.duidui.vo.ResourceVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Resource
    ImgMapper imgMapper;
    @Resource
    AlbumService albumService;

    @Resource
    AlbumMapper albumMapper;

    @Resource
    ImgCollectMapper imgCollectMapper;

    @Resource
    ImgLikeMapper imgLikeMapper;

    @Override
    public void addImage(Img img) {
        img = new Img(null,img.getAid(),new Date(),img.getDataurl(),img.getInfo(),0,0,img.getTags(),null);
        imgMapper.insert(img);
    }

    @Override
    public PageInfo<Img> selectChoiceness(int pageNum, int pageSize) {

        // 首先分页
        PageHelper.startPage(pageNum,pageSize);
        //条件
        List<Img> imgs = imgMapper.selectChoiceness();

        PageInfo<Img> imgPageInfo = new PageInfo<>(imgs);

        //填充其他数据
        for (Img img : imgPageInfo.getList()) {
            //根据专辑id获取专辑信息
            img.setAlbum(albumService.selectAlbumByAid(img.getAid()));
        }

        return imgPageInfo;
    }

    @Override
    public List<Img> selectStateImg(Integer uid) {

        PageHelper.startPage(0,5);

        List<Album> albums = albumService.selectAllAlbum(uid);

        List<Integer> aids = new ArrayList<>();

        for (int i = 0; i < albums.size(); i++) {
            aids.add(albums.get(i).getAid());
        }

        Example example = new Example(Img.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andIn("aid",aids);
        example.orderBy("uptime").desc();

        return imgMapper.selectByExample(example);

    }

    @Override
    public List<Img> selectImgByAid(Integer aid) {
        Example example = new Example(Img.class);
        example.createCriteria().andEqualTo("aid",aid);
        return imgMapper.selectByExample(example);
    }

    @Override
    public Img selectImgByImgId(Integer imgId) {

        Img img = imgMapper.selectByPrimaryKey(imgId);
        //查询图片所属专辑
        Album album = albumService.selectAlbumByAid(img.getAid());
        //为图片赋值专辑属性
        img.setAlbum(album);

        return img;
    }

    @Override
    public PageInfo<Img> selectImageByTagName(ImageVo imageVo) {

        PageHelper.startPage(imageVo.getPageNum(),imageVo.getPageSize());

        Example example = new Example(Img.class);
        example.createCriteria().andLike("tags","%"+imageVo.getTagName()+"%");

        List<Img> imgs = imgMapper.selectByExample(example);

        return new PageInfo<Img>(imgs);
    }

    @Override
    public PageInfo selectSourceByCondition(ResourceVo resourceVo) {

        PageHelper.startPage(resourceVo.getPageNum(),resourceVo.getPageSize());

        //图片
        if (resourceVo.getType()==1){

            Example example = new Example(Img.class);
            example.createCriteria().andLike("info",""+resourceVo.getContent()+"").orLike("tags","%"+resourceVo.getContent()+"%");

            return new PageInfo<Img>(imgMapper.selectByExample(example));
            //专辑
        }else {

            Example example = new Example(Album.class);
            example.createCriteria().andLike("aname","%"+resourceVo.getContent()+"%").orLike("label","%"+resourceVo.getContent()+"%").orLike("info","%"+resourceVo.getContent()+"%");
            PageInfo<Album> albumPageInfo = new PageInfo<>(albumMapper.selectByExample(example));
            for (Album album : albumPageInfo.getList()) {
                album.setImgList(selectImgByAid(album.getAid()));
            }

            return albumPageInfo;
        }
    }

    @Override
    public synchronized boolean collectImg(Integer uid, Integer imgId) {
        //先删除,后添加
        ImgCollect imgCollect = new ImgCollect();
        imgCollect.setImgId(imgId);
        imgCollect.setUid(uid);

        int i = imgCollectMapper.delete(imgCollect);
        //如果删除成功,代表此次操作为取消收藏
        if (i!=0){
            //自增
            imgMapper.delCollect(imgId);
            return false;
        //数据不存在,表名此操作为收藏
        }else {
            imgCollect.setCollectTime(new Date());
            imgCollectMapper.insert(imgCollect);
            imgMapper.addCollect(imgId);
            return true;
        }
    }

    @Override
    public boolean likeImg(Integer uid, Integer imgId) {
        //先删除,后添加
        ImgLike imgLike = new ImgLike();
        imgLike.setImgId(imgId);
        imgLike.setUid(uid);

        int i = imgLikeMapper.delete(imgLike);
        //如果删除成功,代表此次操作为取消收藏
        if (i!=0){
            //自增
            imgMapper.delLike(imgId);
            return false;
            //数据不存在,表名此操作为收藏
        }else {
            imgLike.setLikeTime(new Date());
            imgLikeMapper.insert(imgLike);
            imgMapper.addLike(imgId);
            return true;
        }
    }

    @Override
    public boolean delImgListByImgIdWithUid(Integer imgId, Integer uid) {

        //判断该图片集是否属于该用户
        Img img = imgMapper.selectByPrimaryKey(imgId);
        Album album = albumService.selectAlbumByAid(img.getAid());
        //校验成功,删除
        if (uid==album.getUid()){
            imgMapper.deleteByPrimaryKey(imgId);
            return true;
        }
            return false;
    }

    @Override
    public List<Img> collectList(Integer uid) {

        ImgCollect imgCollect = new ImgCollect();
        imgCollect.setUid(uid);

        List<ImgCollect> select = imgCollectMapper.select(imgCollect);

        List<Img> imgList = new ArrayList<>();

        //根据图片id生成图片集列表
        for (ImgCollect collect : select) {
            imgList.add(imgMapper.selectByPrimaryKey(collect.getImgId()));
        }

        return imgList;
    }

    @Override
    public void updateTags(Img img, Integer uid) {

        Img img1 = imgMapper.selectByPrimaryKey(img.getIid());

        if (img1!=null){

            Album album = albumService.selectAlbumByAid(img1.getAid());

                if (album!=null){

                    if (uid==album.getUser().getUid()){

                        Img img2 = new Img();

                            img2.setTags(img.getTags());
                            img2.setIid(img.getIid());

                        imgMapper.updateByPrimaryKeySelective(img2);
                    }


                }
        }


    }


}
