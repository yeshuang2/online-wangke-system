package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.Exam;
import com.wzz.vo.AddExamByBankVo;
import com.wzz.vo.AddExamByQuestionVo;
import com.wzz.vo.ExamQueryVo;
import com.wzz.vo.PageResponse;

import java.util.List;

/**
 * @author by ysg
 * @implNote 2022/10/20 9:04
 */
public interface ExamService extends IService<Exam> {

    PageResponse<Exam> getExamPage(ExamQueryVo examQueryVo);

    AddExamByQuestionVo getExamInfoById(Integer examId);

    void operationExam(Integer type, String ids);

    void addExamByBank(AddExamByBankVo addExamByBankVo);

    void addExamByQuestionList(AddExamByQuestionVo addExamByQuestionVo);

    void updateExamInfo(AddExamByQuestionVo addExamByQuestionVo);

    List<String> getExamPassRateEchartData();

    List<String> getExamNumbersEchartData();
}
