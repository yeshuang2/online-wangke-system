package com.wzz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 15:29
 *  学生记录题库训练的个数
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("记录题库训练的个数")
@TableName(value = "question_xunlianjilu")
public class QuestionXunLianJiLu {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键 id", example = "1")
    private Integer xunlianjiluId;

    @ApiModelProperty(value = "用户登录名称", example = "")
    private String xunlianjiluUsername;

    @ApiModelProperty(value = "错误个数", example = "")
    private String xunlianjiluFalsegeshu;

    @ApiModelProperty(value = "训练题库名称", example = "")
    private String xunlianjiluTikuname;

    @ApiModelProperty(value = "正确的个数", example = "")
    private String xunlianjiluTruegeshu;

    @ApiModelProperty(value = "总个数", example = "")
    private String xunlianjiluZonggeshu;

    @ApiModelProperty(value = "正确的个数----正确率", example = "")
    private String xunlianjiluGeshuzhengquelv;


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
