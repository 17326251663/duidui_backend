package com.czxy.duidui.controller;

import com.czxy.duidui.domain.Album;
import com.czxy.duidui.domain.Img;
import com.czxy.duidui.domain.User;
import com.czxy.duidui.service.AlbumService;
import com.czxy.duidui.service.ImageService;
import com.czxy.duidui.utils.BaseResult;
import com.czxy.duidui.utils.JwtUtils;
import com.czxy.duidui.utils.Path;
import com.czxy.duidui.utils.RasUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/2
 * @infos
 */
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Resource
    AlbumService albumService;
    @Resource
    ImageService imageService;


    @GetMapping("/{aid}")
    public BaseResult<Album> selectAlbumByAid(@PathVariable("aid") Integer aid){
        return BaseResult.ok("查询成功",albumService.selectAlbumByAid(aid));
    }

    @GetMapping("/withImg/{aid}")
    public BaseResult<Album> selectAlbumWithImgByAid(@PathVariable("aid") Integer aid){
        Album album = albumService.selectAlbumByAid(aid);
        album.setImgList(imageService.selectImgByAid(album.getAid()));
        return BaseResult.ok("查询成功",album);
    }

    @GetMapping
    public BaseResult<List<Album>> selectAllAlbum(HttpServletRequest request) {

        String usermsg = request.getHeader("token");
        System.out.println(usermsg);

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            return BaseResult.ok("查询成功",albumService.selectAllAlbum(user.getUid()));

        } catch (Exception e) {
            return BaseResult.error("请先登录");
        }
    }

    @PostMapping
    public BaseResult<String> addAlbum (@RequestBody Album album, HttpServletRequest request){

        String usermsg = request.getHeader("token");

        if (usermsg==null)
            return BaseResult.error("请先登录");

        try {
            User user = JwtUtils.getObjectFromToken(usermsg, RasUtils.getPublicKey(Path.publicPath), User.class);

            album.setUid(user.getUid());

            try {
                albumService.addAlbum(album);
            } catch (Exception e) {
                return BaseResult.error("专辑名重复");
            }

            return BaseResult.ok("新增成功");

        } catch (Exception e) {
            return BaseResult.error("请先登录");
        }

    }

    @GetMapping("/user/{uid}")
    public BaseResult<List<Img>> selectAllAlbumWithImg(@PathVariable("uid") Integer uid){

            return BaseResult.ok("查询成功",albumService.selectAllAlbumWithImg(uid));

    }
}
