package com.wzz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 9:23
 *     记录学生学习时长
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("记录学生学习时长")
@TableName(value = "study_duration")
public class StudyDuration {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "记录学生学习时长ID", example = "1")
    private Integer studyId;

    @ApiModelProperty(value = "学习当前登录人账户")
    private String studyUsername;

    @ApiModelProperty(value = "学习人所属业务领域")
    private String studyYewu;

    @ApiModelProperty(value = "学习人所属支行")
    private String studyZhihang;

    @ApiModelProperty(value = "网课id")
    private Integer studyWangkeshipingId;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "学习结束时间")
    private Date studyEndtime;


    @ApiModelProperty(value = "更新人")
    private String createBy;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "更新人")
    private String  updateBy;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
