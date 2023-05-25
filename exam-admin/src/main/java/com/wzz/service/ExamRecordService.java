package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.ExamRecord;
import com.wzz.vo.PageResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by ysg
 * @implNote 2022/10/20 9:04
 */
public interface ExamRecordService extends IService<ExamRecord> {

    PageResponse<ExamRecord> getUserGrade(String username, Integer examId, Integer pageNo, Integer pageSize);

    void createExamCertificate(HttpServletResponse response, String examName, Integer examRecordId);

    ExamRecord getExamRecordById(Integer recordId);

    Integer addExamRecord(ExamRecord examRecord, HttpServletRequest request);

    PageResponse<ExamRecord> getExamRecord(Integer examId, Integer pageNo, Integer pageSize);

    void setObjectQuestionScore(Integer totalScore, Integer examRecordId);
}
