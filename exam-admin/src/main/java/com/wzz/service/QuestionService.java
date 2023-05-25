package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.Question;
import com.wzz.vo.PageResponse;
import com.wzz.vo.QuestionVo;

import java.util.List;

/**
 * @author by ysg
 * @implNote 2022/10/20 9:04
 */
public interface QuestionService extends IService<Question> {

    PageResponse<Question> getQuestion(String questionType, String questionBank, String questionContent, Integer pageNo, Integer pageSize);

    QuestionVo getQuestionVoById(Integer id);

    PageResponse<QuestionVo> getQuestionVoByIds(List<Integer> ids);

    void deleteQuestionByIds(String questionIds);

    void addQuestion(QuestionVo questionVo);

    void updateQuestion(QuestionVo questionVo);

}
