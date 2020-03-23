package com.czxy.duidui.vo;

import lombok.Data;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/2/20
 */
@Data
public class ImageReviewVo {
    private Integer type;
    private Integer pageNum;
    private Integer pageSize;
    private Integer imgId;
    private Long parentId;
}
