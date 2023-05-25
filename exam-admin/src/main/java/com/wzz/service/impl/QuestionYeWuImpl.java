package com.wzz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.entity.QuestionYewu;
import com.wzz.mapper.QuestionYeWuMapper;
import com.wzz.service.QuestionYeWuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/18 17:21
 * 这个是实现 角色 去 分配业务信息
 **/
@Service
@RequiredArgsConstructor
public class QuestionYeWuImpl extends ServiceImpl<QuestionYeWuMapper, QuestionYewu> implements QuestionYeWuService {

    private  final QuestionYeWuMapper questionYeWuMapper;

    /**
     * @return 获取页面和 角色信息
     */
    @Override
    public List<QuestionYewu> getAllQuestTionYeWuInfo() {
        return questionYeWuMapper.selectList(null);
    }
}
