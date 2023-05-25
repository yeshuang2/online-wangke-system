package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.QuestionBank;
import com.wzz.entity.StudyDuration;
import com.wzz.vo.PageResponse;
import com.wzz.vo.StudyDurationVo;
import com.wzz.vo.UserInfoVo;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 9:35
 *  记录学生学习时长
 *
 **/
public interface StudyDurationService  extends IService<StudyDuration> {

    /**
     * @param loginName 用户登录的名称
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResponse<StudyDurationVo> getStudyDuration(String loginName, Integer pageNo, Integer pageSize);


}
