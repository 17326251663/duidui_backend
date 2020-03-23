package com.czxy.duidui.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/20
 */
@Table(name = "dd_image_review")
@Data
public class ImageReview {
    @Id
    private Long rid;
    private Integer uid;
    @Column(name = "parentId")
    private Long parentId;
    private String info;
    @Column(name = "infoTime")
    private String infoTime;
    @Column(name = "lookStatus")
    private String lookStatus;
    @Column(name = "imgId")
    private Integer imgId;
    @Column(name = "likeNum")
    private Integer likeNum;
    @Column(name = "toUid")
    private Integer toUid;

    private List<ImageReview> list;

    private Img img;

    private User user;
}
