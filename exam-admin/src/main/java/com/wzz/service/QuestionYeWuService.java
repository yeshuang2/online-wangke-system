package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.MovesBank;
import com.wzz.entity.Question;
import com.wzz.entity.QuestionYewu;

import java.util.List;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/18 17:20
 **/
public interface QuestionYeWuService extends IService<QuestionYewu> {

    List<QuestionYewu> getAllQuestTionYeWuInfo();

}
