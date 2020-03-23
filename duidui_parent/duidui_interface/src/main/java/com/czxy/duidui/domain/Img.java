package com.czxy.duidui.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@Table(name = "dd_img")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Img {
    @Id
    private Integer iid;
    private Integer aid;
    private Date uptime;
    private String dataurl;
    private String info;
    private Integer likenum;
    private Integer collectnum;
    private String tags;

    private Album album;
}
