package com.czxy.duidui.controller;

import com.czxy.duidui.utils.BaseResult;
import com.czxy.duidui.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/16
 * @infos
 */
@RestController
@RequestMapping("/img")
public class ImgController {

    @Value("${gkp.url}")
    private String imgUrl;

    @PostMapping("/upload_head")
    public BaseResult<String> uploadHead(@RequestParam MultipartFile file) throws IOException {
        //校验文件大小
        if (file.getSize()>1024*1024*10){
            return BaseResult.error("文件大小超出限制");
        }
        try {
            String s = UploadUtil.uploadQiniu(file);
            //将fileId返回
            return BaseResult.ok(imgUrl+s);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.error("上传出错");
        }

    }
}
