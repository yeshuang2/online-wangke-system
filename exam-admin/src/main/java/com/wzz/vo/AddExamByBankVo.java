package com.wzz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author by ysg
 * @implNote 2022/11/2 19:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddExamByBankVo {

    @NotBlank
    private String bankNames;

    private Integer examDuration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String examDesc;

    @NotBlank
    private String examName;

    private Integer judgeScore;

    private Integer multipleScore;

    private Integer passScore;

    private Integer shortScore;

    private Integer singleScore;

    private Integer status;

    private Integer type;

    private String password;

}
