package com.czxy.duidui.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/19
 */
@Table(name = "dd_often_label")
@Data
public class OftenLabel {
    @Id
    private Integer oid;
    private String oname;
    private Integer appearnum;
}
