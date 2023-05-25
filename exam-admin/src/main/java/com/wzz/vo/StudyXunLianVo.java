package com.wzz.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/23 16:58
 **/
//这个是记录网课和题库的成绩50% 占比率 进行计算成绩
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyXunLianVo {

    @ApiModelProperty(value = "学习当前登录人账户")
    private String studyUsername;

    private String studyYewu;

    @ApiModelProperty(value = "学习网课视频上传之前名称")
    private String studyWangkename;

    private String studyEndtime;  //结束时间 以这个来记录周 数据（所有的数据 学生进行50%的 计算 给管理员进行查查）

    private int chulimove;

    private String cjmovebili;

    @ApiModelProperty(value = "训练题库名称", example = "")
    private String xunlianjiluTikuname;

    @ApiModelProperty(value = "正确的个数----正确率", example = "")
    private String xunlianjiluGeshuzhengquelv;

    private int chulitiku;


}
