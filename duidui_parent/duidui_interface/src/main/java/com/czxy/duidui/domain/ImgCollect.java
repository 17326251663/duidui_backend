package com.czxy.duidui.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/14
 */
@Table(name = "dd_img_collect")
@Data
@ToString
public class ImgCollect {
    @Id
    private Integer cid;
    private Integer uid;
    @Column(name = "img_id")
    private Integer imgId;
    @Column(name = "collecttime")
    private Date collectTime;


    public Integer getCid() {
        return cid;
    }

    public Integer getUid() {
        return uid;
    }

    public Integer getImgId() {
        return imgId;
    }

    public Date getCollectTime() {
        return collectTime;
    }
}
