package com.czxy.duidui.service.impl;

import com.czxy.duidui.mapper.UserNumMapper;
import com.czxy.duidui.service.AttentionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/3/10
 */
@Service
@Transactional
public class AttentionServiceImpl implements AttentionService {

    @Resource
    UserNumMapper userNumMapper;


    Lock reentrantLock = new ReentrantLock();

    @Override
    public boolean isAttention(Integer uid, Integer fansId) {

        int count = userNumMapper.isAttention(uid,fansId);

       if (count==0)
        return false;
        return true;
    }

    @Override
    public boolean attentionUser(Integer uid, Integer fansId) {

        //获得锁
        reentrantLock.lock();

        try {
            //取关
            int i = userNumMapper.cancelAttention_count(uid, fansId);
            //取关成功,说明本次操作为取消关注
            if (i!=0){
                //减少粉丝的关注数
                userNumMapper.delAttentionNum(fansId);
                //减少关注的粉丝数
                userNumMapper.delFansNum(uid);

                return false;
                //取关成功,说明此次操作为关注
            }else {
                //添加关联记录
                userNumMapper.addAttention_count(uid,fansId);
                //添加粉丝的关注数
                userNumMapper.addAttentionNum(fansId);
                //添加关注的粉丝数
                userNumMapper.addFansNum(uid);

                return true;
            }
        } finally {
            //释放锁
            reentrantLock.unlock();
        }
    }
}
