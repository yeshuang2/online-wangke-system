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
 * @编辑时间：2023/4/18 14:44
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("网课实体")
@TableName(value = "moves_bank")
public class MovesBank {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键 题库id", example = "1")
    private Integer wangkeid;

    @ApiModelProperty(value = "网课名称", example = "")
    private String wangkeName;
}
