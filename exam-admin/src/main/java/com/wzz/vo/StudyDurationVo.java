package com.wzz.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzz.entity.StudyDuration;
import com.wzz.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 10:09
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyDurationVo {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "记录学生学习时长ID", example = "1")
    private Integer studyId;

    @ApiModelProperty(value = "学习当前登录人账户")
    private String studyUsername;

    @ApiModelProperty(value = "网课id")
    private Integer studyWangkeshipingId;

    @ApiModelProperty(value = "学习人所属业务领域")
    private String studyYewu;

    @ApiModelProperty(value = "学习人所属支行")
    private String studyZhihang;

    @ApiModelProperty(value = "学习网课视频上传之前名称")
    private String studyWangkename;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "学习开始时间")
    private Date studyStarttime;

    @ApiModelProperty(value = "单词学习时长")
    private Integer studyShichang;

    @ApiModelProperty(value = "累积学习总时长")
    private Integer  studyLeijishichang;


    @ApiModelProperty(value = "学习总时长")
    private String studyTotalshichang;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "学习结束时间")
    private Date studyDndtime;


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


    @ApiModelProperty(value = "视频状态")
    private String MovesZt;

    public static StudyDurationVo fromStudyDuration(StudyDuration studyDuration) {
        StudyDurationVo studyDurationVoVo = new StudyDurationVo();
        BeanUtils.copyProperties(studyDuration, studyDurationVoVo);
        return studyDurationVoVo;
    }

}
