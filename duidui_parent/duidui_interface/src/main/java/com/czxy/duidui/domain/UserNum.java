package com.czxy.duidui.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/11
 */
@Table(name = "dd_user_num")
@Data
@EqualsAndHashCode
@ToString
public class UserNum {
    @Id
    private Integer nid;
    private Integer uid;
    @Column(name = "aNum")
    private Integer aNum;
    @Column(name = "fNum")
    private Integer fNum;

}
