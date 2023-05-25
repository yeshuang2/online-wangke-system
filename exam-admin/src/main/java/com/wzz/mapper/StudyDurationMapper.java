package com.wzz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzz.entity.StudyDuration;
import com.wzz.vo.PageResponse;
import com.wzz.vo.StudyDurationVo;
import org.springframework.stereotype.Repository;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 9:34
 * 记录学习时长
 **/

@Repository
public interface StudyDurationMapper  extends BaseMapper<StudyDuration> {

    /**
     * @param loginName 用户登录的名称
     * @param pageNo
     * @param pageSize
     *  获取用户学习的数据信息
     * @return
     */
    PageResponse<StudyDurationVo> getStudyDuration(String loginName, Integer pageNo, Integer pageSize);

}
