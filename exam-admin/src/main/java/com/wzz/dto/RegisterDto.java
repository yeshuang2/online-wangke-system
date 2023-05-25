package com.wzz.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {

    @NotBlank
    private String username;

    @NotBlank
    @Length(min = 5, max = 20)
    private String password;

    private String trueName;

//    基层员工、部门经理、支行行长三类职业
    private String threevalue;
//选择某某支行
    private String zhihangvalue;

}
