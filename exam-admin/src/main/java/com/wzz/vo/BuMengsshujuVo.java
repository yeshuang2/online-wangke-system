package com.wzz.vo;

import com.wzz.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/21 16:41
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuMengsshujuVo {


    private String username;

    //    基层员工、部门经理、支行行长三类职业
    private String threevalue;
    //选择某某支行
    private String zhihangvalue;

    private String studyYewu;

    private String cjmovebili;
    private int chulimove;
    private int chulitiku;

    private String  rank;

    @ApiModelProperty(value = "训练题库名称", example = "")
    private String xunlianjiluTikuname;

    @ApiModelProperty(value = "错误个数", example = "")
    private String xunlianjiluFalsegeshu;

    @ApiModelProperty(value = "正确的个数", example = "")
    private String xunlianjiluTruegeshu;

    @ApiModelProperty(value = "总个数", example = "")
    private String xunlianjiluZonggeshu;

    @ApiModelProperty(value = "正确的个数----正确率", example = "")
    private String xunlianjiluGeshuzhengquelv;

    public static BuMengsshujuVo fromUser(BuMengsshujuVo buMengsshujuVo) {
        BuMengsshujuVo BuMengVo = new BuMengsshujuVo();
        BeanUtils.copyProperties(buMengsshujuVo, BuMengVo);
        return BuMengVo;
    }

}
