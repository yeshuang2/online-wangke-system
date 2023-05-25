package com.wzz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/18 17:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("问题业务--实体")
@TableName(value = "question_yewu")
public class QuestionYewu {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键 题库id", example = "1")
    private Integer yewuid;

    @ApiModelProperty(value = "业务角色", example = "行长")
    private String yewuName;

    @ApiModelProperty(value = "业务的id", example = "1")
    private String yewuQuBinkid;

    @ApiModelProperty(value = "业务的id--名称", example = "贷款项目")
    private String yewuQuBinkname;

    public QuestionYewu(String yewuName, String handleHaveBankIds, StringBuilder bankNames) {
    }
}
