package com.wzz.vo;

import com.wzz.entity.QuestionBank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by ysg
 * @implNote 2022/10/28 20:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankHaveQuestionSum {
    private QuestionBank questionBank;
    //单选数量
    private Integer singleChoice;
    //多选数量
    private Integer multipleChoice;
    //判断数量
    private Integer judge;
    //简答数量
    private Integer shortAnswer;
}
