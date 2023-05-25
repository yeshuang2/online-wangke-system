package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.Moves;
import com.wzz.entity.MovesBank;
import com.wzz.entity.QuestionBank;

import java.util.List;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/18 14:17
 **/
public interface WangKeService extends IService<MovesBank> {

    List<MovesBank> getAllMovesBankInfo();

}
