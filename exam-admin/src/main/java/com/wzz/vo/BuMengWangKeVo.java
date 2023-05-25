package com.wzz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/21 20:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BuMengWangKeVo {

    private String username;

    //    基层员工、部门经理、支行行长三类职业
    private String threevalue;
    //选择某某支行
    private String zhihangvalue;

    @ApiModelProperty(value = "学习人所属业务领域")
    private String studyYewu;

    @ApiModelProperty(value = "学习网课视频上传之前名称")
    private String studyWangkename;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "学习开始时间")
    private Date studyStarttime;


    @ApiModelProperty(value = "学习时长-秒单位")
    private Integer studyShichang;

    @ApiModelProperty(value = "累积学习时长")
    private Integer  studyLeijishichang;

    @ApiModelProperty(value = "学习总时长-秒单位")
    private String studyTotalshichang;


    @ApiModelProperty(value = "累积学习时长/学习总时长=比例")
    private String remark;

}
