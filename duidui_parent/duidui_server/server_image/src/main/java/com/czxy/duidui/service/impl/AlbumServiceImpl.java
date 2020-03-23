package com.czxy.duidui.service.impl;


import com.czxy.duidui.domain.Album;
import com.czxy.duidui.feign.UserFeign;
import com.czxy.duidui.mapper.AlbumMapper;
import com.czxy.duidui.mapper.OftenLabelMapper;
import com.czxy.duidui.service.AlbumService;
import com.czxy.duidui.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/2
 * @infos
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Resource
    AlbumMapper albumMapper;

    @Resource
    UserFeign userFeign;

    @Resource
    ImageService imageService;

    @Resource
    OftenLabelMapper oftenLabelMapper;


    @Override
    public Album selectAlbumByAid(Integer aid) {
        Album album1 = albumMapper.selectByPrimaryKey(aid);
        //查询专辑所属用户
        album1.setUser(userFeign.selectUserByUid(album1.getUid()).getData());

        return album1;
    }

    @Override
    public List<Album> selectAllAlbum(Integer uid) {
        Example example = new Example(Album.class);
        example.createCriteria().andEqualTo("uid",uid);

        return albumMapper.selectByExample(example);
    }

    @Override
    public void addAlbum(Album album) {
        album.setAid(null);

        if (album.getLabel()!=null&&!album.getLabel().equals(""))
        {
            String[] labels = album.getLabel().split(" ");
            for (String label : labels) {
                oftenLabelMapper.call_add_label(label);
            }
        }

        albumMapper.insert(album);
    }

    @Override
    public List<Album> selectAllAlbumWithImg(Integer uid) {

        Example example = new Example(Album.class);
        example.createCriteria().andEqualTo("uid",uid);

        List<Album> albums = albumMapper.selectByExample(example);

        for (Album album : albums) {
            album.setImgList(imageService.selectImgByAid(album.getAid()));
        }

        return albums;
    }
}
