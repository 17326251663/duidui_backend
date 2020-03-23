package com.czxy.duidui.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/20
 * @infos
 */
@Table(name = "dd_user")
@Data
public class User implements Serializable,Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private String username;
    private String telephone;
    private String email;
    private String password;
    @Column(name = "imageUrl")
    private String imageUrl;

    @Transient
    private String code;

    private Integer[] likeMCategory;

    private UserNum userNum;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer[] getLikeMCategory() {
        return likeMCategory;
    }

    public void setLikeMCategory(Integer[] likeMCategory) {
        this.likeMCategory = likeMCategory;
    }
}
