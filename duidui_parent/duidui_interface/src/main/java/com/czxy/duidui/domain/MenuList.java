package com.czxy.duidui.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@Table(name = "dd_list")
@Data
public class MenuList {

    @Id
    private Integer lid;
    private String name;
    private String path;
}

