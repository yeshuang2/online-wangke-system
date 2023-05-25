package com.wzz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.annotation.Cache;
import com.wzz.entity.Moves;
import com.wzz.entity.MovesBank;
import com.wzz.entity.QuestionBank;
import com.wzz.mapper.WangKeMapper;
import com.wzz.service.WangKeService;
import com.wzz.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/18 14:18
 **/
@Service
@RequiredArgsConstructor
public class WangKeServiceImpl extends ServiceImpl<WangKeMapper, MovesBank> implements WangKeService {

    private final WangKeMapper wangKeMapper;

    /**
     * @return 获取网课类型数据
     */
    @Override
//    @Cache(prefix = "wangkeinfo", ttl = 10, timeUnit = TimeUnit.HOURS) //缓存
    public List<MovesBank> getAllMovesBankInfo() {
        return wangKeMapper.selectList(null);
    }
}
