package com.czxy.duidui.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@Table(name = "dd_album")
@Data
public class Album {
    @Id
    private Integer aid;
    private String aname;
    private Integer uid;
    private String info;
    private String label;

    private User user;
    private List<Img> imgList;

}
