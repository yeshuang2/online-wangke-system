package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.dto.AddUserDto;
import com.wzz.dto.LoginDto;
import com.wzz.dto.RegisterDto;
import com.wzz.dto.UpdateUserInfoDto;
import com.wzz.entity.User;
import com.wzz.vo.PageResponse;
import com.wzz.vo.UserInfoVo;

import java.util.List;

/**
 * @author by ysg
 * @implNote 2022/10/20 9:04
 */
public interface UserService extends IService<User> {

    String register(RegisterDto registerDto);

    Boolean checkUsername(String username);

    String login(LoginDto loginDto);

    User getUserByUsername(String username);

    // 这里要reset cache 所以必须要有更新后的数据返回
    User updateUserInfo(UpdateUserInfoDto updateUserInfoDto);

    PageResponse<UserInfoVo> getUser(String loginName, String trueName, Integer pageNo, Integer pageSize);

    void handlerUser(Integer type, String userIds);

    void addUser(AddUserDto addUserDto);

    UserInfoVo getUserInfoById(Integer userId);

    List<UserInfoVo> getUserInfoByIds(List<Integer> userIds);
}
