package com.czxy.duidui.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/3/8
 */
@Table(name = "dd_img_like")
@Data
public class ImgLike {
    @Id
    private Integer lid;
    @Column(name = "imgId")
    private Integer imgId;
    private Integer uid;
    @Column(name = "likeTime")
    private Date likeTime;
}
