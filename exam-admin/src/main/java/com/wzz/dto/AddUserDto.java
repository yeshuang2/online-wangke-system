package com.wzz.dto;

import com.wzz.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddUserDto {

    @NotBlank
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Integer roleId;

//    角色
    @NotBlank
    private String threevalue;

    private String trueName;

    public User toUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

}
