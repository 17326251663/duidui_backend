package com.czxy.duidui.service;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/3/10
 */
public interface AttentionService {
    boolean isAttention(Integer uid, Integer fansId);

    boolean attentionUser(Integer uid, Integer fansId);
}
