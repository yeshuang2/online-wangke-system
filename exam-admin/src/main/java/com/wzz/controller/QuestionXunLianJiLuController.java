package com.wzz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzz.entity.QuestionXunLianJiLu;
import com.wzz.entity.StudyDuration;
import com.wzz.service.QuestionXunLianJiLuService;
import com.wzz.vo.CommonResult;
import com.wzz.vo.PageResponse;
import com.wzz.vo.QuestionXunLianJiLuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 15:37
 **/

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "记录学生题库训练的记录")
@RequestMapping(value = "/xunlinajiluinfo")
public class QuestionXunLianJiLuController {

    @Autowired
    private QuestionXunLianJiLuService questionXunLianJiLuService;


    @GetMapping("/getQuestionXunLianJiLuInfo")
    @ApiOperation("记录学生题库训练的记录,可分页 ----> 查询条件(可无)(username),必须有的(pageNo,pageSize)")
    public CommonResult<PageResponse<QuestionXunLianJiLuVo>> getQuestionXunLianJiLu(
            @RequestParam(required = false) String xunlianjiluUsername, Integer pageNo, Integer pageSize) {
        return CommonResult.<PageResponse<QuestionXunLianJiLuVo>>builder()
                .data(questionXunLianJiLuService.getQuestionXunLianJiLu(xunlianjiluUsername, pageNo, pageSize))
                .message("获取记录学生题库训练的记录成功")
                .build();
    }

    @PostMapping("/addQuestionXunLianJiLuInfo")
    @ApiOperation("添加业务")
    public CommonResult<Void> addQuestionXunLianJiLuWrapper(
            @RequestParam(required = false) String xunlianjiluUsername,
            @RequestParam(required = false) String xunlianjiluFalsegeshu,
            @RequestParam(required = false) String xunlianjiluTikuname,
            @RequestParam(required = false) String xunlianjiluTruegeshu,
            @RequestParam(required = false) String xunlianjiluZonggeshu,
            @RequestParam(required = false) String xunlianjiluGeshuzhengquelv
    ) {
        QueryWrapper QuestionXunLianJiLuWrapper = new QueryWrapper<>();
        Map<String, Object> questionXunLianJiLuWrapper = new HashMap<>();
        questionXunLianJiLuWrapper.put("xunlianjilu_username", xunlianjiluUsername);
        questionXunLianJiLuWrapper.put("xunlianjilu_tikuname", xunlianjiluTikuname);
        QuestionXunLianJiLuWrapper.allEq(questionXunLianJiLuWrapper);
        QuestionXunLianJiLu questionXunLianJiLu = questionXunLianJiLuService.getOne(QuestionXunLianJiLuWrapper);
        if (questionXunLianJiLu != null) { //存在 就进行更新
        //   进行条件更新操作
            QueryWrapper QuestionXunLianJiLuWrapperupdate = new QueryWrapper<>();
            Map<String, Object> mapquestionXunLianJiLuWrapperupdate = new HashMap<>();
            System.out.println(xunlianjiluUsername+"--------"+xunlianjiluTikuname);
            mapquestionXunLianJiLuWrapperupdate.put("xunlianjilu_username", xunlianjiluUsername);
            mapquestionXunLianJiLuWrapperupdate.put("xunlianjilu_tikuname", xunlianjiluTikuname);
             QuestionXunLianJiLuWrapper.allEq(mapquestionXunLianJiLuWrapperupdate);// 更新操作

            QuestionXunLianJiLu updatequestionXunLianJiLu = new QuestionXunLianJiLu();
            updatequestionXunLianJiLu.setXunlianjiluFalsegeshu(xunlianjiluFalsegeshu);//错误个数
            updatequestionXunLianJiLu.setXunlianjiluTruegeshu(xunlianjiluTruegeshu);// 正确个数
            updatequestionXunLianJiLu.setXunlianjiluZonggeshu(xunlianjiluZonggeshu); //总数
            updatequestionXunLianJiLu.setXunlianjiluGeshuzhengquelv(xunlianjiluGeshuzhengquelv);  //正确率
            updatequestionXunLianJiLu.setCreateTime(new Date());      //刷新更新时间
            questionXunLianJiLuService.update(updatequestionXunLianJiLu,QuestionXunLianJiLuWrapper);
            return CommonResult.<Void>builder().message("更新学习题库训练成绩成功").build();
        } else { // 不存在就进行 add
            QuestionXunLianJiLu addquestionXunLianJiLu = new QuestionXunLianJiLu();
            addquestionXunLianJiLu.setXunlianjiluFalsegeshu(xunlianjiluFalsegeshu);//错误个数
            addquestionXunLianJiLu.setXunlianjiluTruegeshu(xunlianjiluTruegeshu);// 正确个数
            addquestionXunLianJiLu.setXunlianjiluGeshuzhengquelv(xunlianjiluGeshuzhengquelv);  //正确率
            addquestionXunLianJiLu.setXunlianjiluZonggeshu(xunlianjiluZonggeshu); //总数
            addquestionXunLianJiLu.setCreateBy(xunlianjiluUsername); //当前用户登录的信息
            addquestionXunLianJiLu.setXunlianjiluUsername(xunlianjiluUsername); //当前用户登录的信息
            addquestionXunLianJiLu.setXunlianjiluTikuname(xunlianjiluTikuname); //题库名称
            addquestionXunLianJiLu.setCreateTime(new Date());      //创建时间
            questionXunLianJiLuService.save(addquestionXunLianJiLu);
            return CommonResult.<Void>builder().message("保存学习题库训练成绩成功").build();
        }
    }


}
