package com.wzz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzz.entity.StudyDuration;
import com.wzz.service.StudyDurationService;
import com.wzz.vo.CommonResult;
import com.wzz.vo.PageResponse;
import com.wzz.vo.StudyDurationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 10:03
 **/

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "记录学生学习时长")
@RequestMapping(value = "/studyduration")
public class StudyDurationController {

    @Autowired
    private StudyDurationService studydurationService;  //学生学习记录


    @GetMapping("/getStudydurationInfo")
    @ApiOperation("获取用户信息,可分页 ----> 查询条件(可无)(username),必须有的(pageNo,pageSize)")
    public CommonResult<PageResponse<StudyDurationVo>> getUser(@RequestParam(required = false) String loginName,
                                                               Integer pageNo, Integer pageSize) {
        return CommonResult.<PageResponse<StudyDurationVo>>builder()
                .data(studydurationService.getStudyDuration(loginName, pageNo, pageSize))
                .message("获取记录学生学习时长成功")
                .build();
    }


    @PostMapping("/addStudydurationInfo")
    @ApiOperation("添加业务")
    public CommonResult<Void> addDurationWrapper(@RequestParam(required = false) String studyUsername,
                                                 @RequestParam(required = false) String studyWangkename,
                                                 @RequestParam Integer studyWangkeshipingId,
                                                 @RequestParam(required = false) String studyYewu,
                                                 @RequestParam(required = false) String createBy) {
        QueryWrapper StudyDurationWrapper = new QueryWrapper<>();
        Map<String, Object> StudyaddDurationWrapper = new HashMap<>();
        StudyaddDurationWrapper.put("study_username", studyUsername);
        StudyaddDurationWrapper.put("study_wangkename", studyWangkename);
        StudyaddDurationWrapper.put("study_wangkeshiping_id", studyWangkeshipingId);
        StudyDurationWrapper.allEq(StudyaddDurationWrapper);
        StudyDuration studyDurationOne = studydurationService.getOne(StudyDurationWrapper);
        if (studyDurationOne != null) { //存在就不进行操作
            return CommonResult.<Void>builder().build();
        } else { // 不存在就进行add
            StudyDuration studyDuration = new StudyDuration(); //学习网课id
            studyDuration.setStudyWangkeshipingId(studyWangkeshipingId);
            studyDuration.setStudyUsername(studyUsername);
            studyDuration.setStudyWangkename(studyWangkename);// 网课名称
            studyDuration.setStudyYewu(studyYewu);  //学习所属的业务
            studyDuration.setCreateBy(createBy);
            studyDuration.setCreateTime(new Date());      //创建时间
            studyDuration.setStudyStarttime(new Date()); //开始时间
            studyDuration.setStudyShichang(0); //初始化时长0
            studyDuration.setStudyLeijishichang(0); //初始累积0
            studydurationService.save(studyDuration);
            return CommonResult.<Void>builder().build();
        }
    }

    @PostMapping("/updateStudyDurationInfoById")
    @ApiOperation("更新业务名称")
    public CommonResult<Void> updateQuestion(@RequestBody @Valid StudyDuration studyDuration) {
        studyDuration.setUpdateTime(new Date());      //更新时间study_dndtime
        studyDuration.setStudyEndtime(new Date());    // 学生结束时间
//        studyDuration.setCreateBy(studyDuration.getCreateBy());  // 更新学生学习开始时间
        studyDuration.setStudyStarttime(new Date());  // 更新学生学习开始时间


        QueryWrapper StudyDurationWrapper = new QueryWrapper<>();
//        更新网课id 进行更新操作
        StudyDurationWrapper.eq("study_wangkeshiping_id", studyDuration.getStudyWangkeshipingId());
        StudyDurationWrapper.eq("study_username", studyDuration.getStudyUsername());
        //        更新累积时长
        QueryWrapper updateStudyDurationWrapper = new QueryWrapper<>();
        Map<String, Object> StudyaddDurationWrapper = new HashMap<>();
        StudyaddDurationWrapper.put("study_username", studyDuration.getStudyUsername());
        StudyaddDurationWrapper.put("study_wangkename", studyDuration.getStudyWangkename());
//        System.out.println(studyDuration.getCreateBy());
//        StudyaddDurationWrapper.put("create_by", studyDuration.getCreateBy());
        StudyaddDurationWrapper.put("study_wangkeshiping_id", studyDuration.getStudyWangkeshipingId());
        updateStudyDurationWrapper.allEq(StudyaddDurationWrapper);
        StudyDuration leijjilishichangone = studydurationService.getOne(updateStudyDurationWrapper);
        int leijizhichangtimes = leijjilishichangone.getStudyShichang() + studyDuration.getStudyShichang();
        System.out.println(leijizhichangtimes+"---------累积时长----------");
        studyDuration.setStudyLeijishichang(leijizhichangtimes); //记录累积时长时间
        studydurationService.update(studyDuration, StudyDurationWrapper);
        return CommonResult.<Void>builder()
                .build();
    }


}
