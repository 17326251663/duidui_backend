package com.czxy.duidui.service;

import com.czxy.duidui.domain.Album;

import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/2
 * @infos
 */
public interface AlbumService {

    public Album selectAlbumByAid(Integer aid);

    List<Album> selectAllAlbum(Integer uid);

    void addAlbum(Album album);

    List<Album> selectAllAlbumWithImg(Integer uid);
}
