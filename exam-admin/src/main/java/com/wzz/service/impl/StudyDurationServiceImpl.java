package com.wzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.entity.StudyDuration;
import com.wzz.entity.User;
import com.wzz.entity.UserRole;
import com.wzz.mapper.StudyDurationMapper;
import com.wzz.mapper.UserMapper;
import com.wzz.service.StudyDurationService;
import com.wzz.vo.PageResponse;
import com.wzz.vo.StudyDurationVo;
import com.wzz.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wzz.utils.CommonUtils.setLikeWrapper;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 9:36
 *  记录学生学习时长
 **/

@Service
@RequiredArgsConstructor
public class StudyDurationServiceImpl extends ServiceImpl<StudyDurationMapper, StudyDuration> implements StudyDurationService {


    private final StudyDurationMapper studyDurationMapper;


    @Override
    public PageResponse<StudyDurationVo> getStudyDuration(String loginName, Integer pageNo, Integer pageSize) {
        IPage<StudyDuration> StudyDurationPage = new Page<>(pageNo, pageSize);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("study_username", loginName);;

        QueryWrapper<StudyDuration> studyDurationQueryWwrapper = new QueryWrapper<>();
        setLikeWrapper(studyDurationQueryWwrapper, queryParams);
        studyDurationQueryWwrapper.orderByDesc("create_time");

        StudyDurationPage = studyDurationMapper.selectPage(StudyDurationPage, studyDurationQueryWwrapper);
        List<StudyDurationVo> records = StudyDurationPage.getRecords().stream().map(StudyDurationVo::fromStudyDuration).collect(Collectors.toList());

        return PageResponse.<StudyDurationVo>builder().data(records).total(StudyDurationPage.getTotal()).build();
    }
}
