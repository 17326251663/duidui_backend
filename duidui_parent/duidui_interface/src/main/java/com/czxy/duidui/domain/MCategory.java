package com.czxy.duidui.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/17
 * @infos
 */
@Table(name = "dd_category")
@Data
public class MCategory {
    @Id
    private Integer cid;
    private String cname;
    @Column(name = "isparent")
    private Integer isParent;
    @Column(name = "parent_id")
    private Integer parentId;

    private List<MCategory> mList;
}
