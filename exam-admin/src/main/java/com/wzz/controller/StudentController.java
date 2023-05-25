package com.wzz.controller;

import com.wzz.entity.ExamQuestion;
import com.wzz.entity.ExamRecord;
import com.wzz.service.ExamQuestionService;
import com.wzz.service.QuestionService;
import com.wzz.service.impl.ExamRecordServiceImpl;
import com.wzz.vo.CommonResult;
import com.wzz.vo.PageResponse;
import com.wzz.vo.QuestionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author by ysg
 * @implNote 2022/11/7 19:44
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "学生权限相关的接口")
@RequestMapping(value = "/student")
public class StudentController {

    private final ExamRecordServiceImpl examRecordService;

    private final QuestionService questionService;

    private final ExamQuestionService examQuestionService;

    @GetMapping("/getMyGrade")
    @ApiOperation("获取个人成绩(分页 根据考试名查询)")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "系统唯一用户名", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "pageNo", value = "当前页面数", required = true, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "pageSize", value = "当前页面大小", required = true, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "examId", value = "考试唯一id", dataType = "int", paramType = "query")})
    public CommonResult<PageResponse<ExamRecord>> getMyGrade(String username, Integer pageNo, Integer pageSize, @RequestParam(required = false) Integer examId) {
        return CommonResult.<PageResponse<ExamRecord>>builder().data(examRecordService.getUserGrade(username, examId, pageNo, pageSize)).build();
    }

    @GetMapping("/getCertificate")
    @ApiOperation("生成证书接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "examName", value = "考试名称", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "examRecordId", value = "考试记录id", required = true, dataType = "int", paramType = "query")})
    public void getCertificate(HttpServletResponse response, @RequestParam(name = "examName") String examName, @RequestParam(name = "examRecordId") Integer examRecordId) {
        examRecordService.createExamCertificate(response, examName, examRecordId);
    }

    @PostMapping("/addExamRecord")
    @ApiOperation("保存考试记录信息,返回保存记录的id")
    @ApiImplicitParams({@ApiImplicitParam(name = "examRecord", value = "考试记录实体对象", required = true, dataType = "examRecord", paramType = "body")})
    public CommonResult<Integer> addExamRecord(@RequestBody ExamRecord examRecord, HttpServletRequest request) {
        return CommonResult.<Integer>builder().data(examRecordService.addExamRecord(examRecord, request)).build();
    }

    @GetMapping("/getQuestionById/{id}")
    @ApiOperation("根据id获取题目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问题id", required = true, dataType = "int", paramType = "path")
    })
    public CommonResult<QuestionVo> getQuestionById(@PathVariable("id") Integer id) {
        return CommonResult.<QuestionVo>builder()
                .data(questionService.getQuestionVoById(id))
                .build();
    }

    @GetMapping("/getQuestionByIds")
    @ApiOperation("根据id集合获取题目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "问题id集合", required = true, dataType = "int", paramType = "params")
    })
    public CommonResult<PageResponse<QuestionVo>> getQuestionById(@RequestParam("ids") List<Integer> ids) {
        return CommonResult.<PageResponse<QuestionVo>>builder()
                .data(questionService.getQuestionVoByIds(ids))
                .build();
    }

    @GetMapping("/getExamRecordById/{recordId}")
    @ApiOperation("根据考试的记录id查询用户考试的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordId", value = "考试记录id", required = true, dataType = "int", paramType = "query")
    })
    public CommonResult<ExamRecord> getExamRecordById(@PathVariable Integer recordId) {
        return CommonResult.<ExamRecord>builder()
                .data(examRecordService.getExamRecordById(recordId))
                .build();
    }

    @GetMapping("/getExamQuestionByExamId/{examId}")
    @ApiOperation("根据考试id查询考试中的每一道题目id和分值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examId", value = "考试id", required = true, dataType = "int", paramType = "query")
    })
    public CommonResult<ExamQuestion> getExamQuestionByExamId(@PathVariable Integer examId) {
        return CommonResult.<ExamQuestion>builder()
                .data(examQuestionService.getExamQuestionByExamId(examId))
                .build();
    }
}
