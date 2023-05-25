package com.wzz.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzz.entity.Moves;
import com.wzz.entity.User;
import io.swagger.annotations.ApiModel;
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
 * @编辑时间：2023/4/17 10:27
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体")
@TableName(value = "moves")
public class MovesVo {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "网课 公告moveid", example = "1")
    private Integer moveid;

    @ApiModelProperty(value = "网课分类")
    private String moveCategorize;


    @ApiModelProperty(value = "网课名称")
    private String moveName;

    @ApiModelProperty(value = "网课名称")
    private String moveNameOld;

    @ApiModelProperty(value = "网课路径")
    private String moveUrl;

    @ApiModelProperty(value = "网课开始时间")
    private String moveStart;

    @ApiModelProperty(value = "网课视频结束时间")
    private String moveEnd;

    @ApiModelProperty(value = "网课视频观看记录")
    private String moveTimejilu;

    @ApiModelProperty(value = "网课视频总时长")
    private String moveTime;

    @ApiModelProperty(value = "网课管理id 1")
    private String moveUserId;

    @ApiModelProperty(value = "网课管理id 2")
    private String moveUserIdtwo;

    @ApiModelProperty(value = "网课状态")
    private String status;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "网课名称")
    private String remark;

    public static MovesVo fromMoves(Moves moves) {
        MovesVo Movesvo = new MovesVo();
        BeanUtils.copyProperties(moves, Movesvo);
        return Movesvo;
    }

}
