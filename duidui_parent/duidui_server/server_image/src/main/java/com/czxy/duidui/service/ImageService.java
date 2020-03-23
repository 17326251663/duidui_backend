package com.czxy.duidui.service;

import com.czxy.duidui.domain.Img;
import com.czxy.duidui.vo.ImageVo;
import com.czxy.duidui.vo.ResourceVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
public interface ImageService {

    void addImage(Img img);

    PageInfo<Img> selectChoiceness(int pageNum, int pageSize);

    List<Img> selectStateImg(Integer uid);

    List<Img> selectImgByAid(Integer aid);

    Img selectImgByImgId(Integer imgId);

    PageInfo<Img> selectImageByTagName(ImageVo imageVo);

    PageInfo selectSourceByCondition(ResourceVo resourceVo);

    boolean collectImg(Integer uid, Integer imgId);

    boolean likeImg(Integer uid, Integer imgId);

    boolean delImgListByImgIdWithUid(Integer imgId, Integer uid);

    List<Img> collectList(Integer uid);

    void updateTags(Img img, Integer uid);
}
