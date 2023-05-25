package com.wzz.vo;

import com.wzz.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {

    private Integer id;

    private String username;

    private String trueName;

    private Integer roleId;

    private Integer status;

    private Date createDate;

    //    基层员工、部门经理、支行行长三类职业
    private String threevalue;
    //选择某某支行
    private String zhihangvalue;

    public static UserInfoVo fromUser(User user) {
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }
}
