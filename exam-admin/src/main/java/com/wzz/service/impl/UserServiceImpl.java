package com.wzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.annotation.Cache;
import com.wzz.dto.AddUserDto;
import com.wzz.dto.LoginDto;
import com.wzz.dto.RegisterDto;
import com.wzz.dto.UpdateUserInfoDto;
import com.wzz.entity.User;
import com.wzz.exception.BusinessException;
import com.wzz.exception.CommonErrorCode;
import com.wzz.mapper.UserMapper;
import com.wzz.service.UserService;
import com.wzz.utils.JwtUtils;
import com.wzz.utils.SaltEncryption;
import com.wzz.vo.PageResponse;
import com.wzz.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.wzz.utils.CommonUtils.setLikeWrapper;
import static com.wzz.vo.UserInfoVo.fromUser;

/**
 * @author by ysg
 * @implNote 2022/10/20 9:05
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    /**
     * @param registerDto 前端
     * @return
     */
    @Override
    public String register(RegisterDto registerDto) {
        if (!checkUsername(registerDto.getUsername())) {
            throw new BusinessException(CommonErrorCode.E_100103);
        }
        // 盐值
        String salt = UUID.randomUUID().toString().substring(0, 6);
        String newPwd = SaltEncryption.saltEncryption(registerDto.getPassword(), salt);
        User user = new User();
        BeanUtils.copyProperties(registerDto, user);
        if (registerDto.getThreevalue().equals("部门经理")) {
            user.setRoleId(2);
            user.setPassword(newPwd);
            user.setThreevalue(registerDto.getThreevalue());      //基层员工、部门经理、支行行长 三类职业
            user.setZhihangvalue(registerDto.getZhihangvalue());  //支行
            user.setSalt(salt);
            user.setCreateDate(new Date());
            userMapper.insert(user);
            // 发放token令牌
            return JwtUtils.createToken(user);
        } else if (registerDto.getThreevalue().equals("支行行长")) {
            user.setRoleId(3);
            user.setPassword(newPwd);
            user.setThreevalue(registerDto.getThreevalue());      //基层员工、部门经理、支行行长 三类职业
            user.setZhihangvalue(registerDto.getZhihangvalue());  //支行
            user.setSalt(salt);
            user.setCreateDate(new Date());
            userMapper.insert(user);
            // 发放token令牌
            return JwtUtils.createToken(user);
        } else {
            user.setPassword(newPwd);
            user.setThreevalue(registerDto.getThreevalue());      //基层员工、部门经理、支行行长 三类职业
            user.setZhihangvalue(registerDto.getZhihangvalue());  //支行
            user.setSalt(salt);
            user.setRoleId(1);
            user.setCreateDate(new Date());
            userMapper.insert(user);
            // 发放token令牌
            return JwtUtils.createToken(user);
        }


    }

    @Override
    public Boolean checkUsername(String username) {
        return userMapper.selectCount(new QueryWrapper<User>().eq("username", username)) < 1;
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        if (user == null) {
            throw new BusinessException(CommonErrorCode.E_100102);
        }
        String saltPassword = SaltEncryption.saltEncryption(loginDto.getPassword(), user.getSalt());
        // 对用户输入的密码加密后 对比数据库的密码 并且用户的状态是正常的
        if (saltPassword.equals(user.getPassword()) && user.getStatus() == 1) {
            // 发放token令牌
            return JwtUtils.createToken(user);
        } else {
            // 密码错误 或者 账号封禁
            throw new BusinessException(CommonErrorCode.E_100101);
        }
    }

    @Override
    @Cache(prefix = "user", suffix = "#username", ttl = 10, randomTime = 2, timeUnit = TimeUnit.HOURS)
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    @Cache(prefix = "user", suffix = "#updateUserInfoDto.getUsername()", ttl = 10, randomTime = 2, timeUnit = TimeUnit.HOURS, resetCache = true)
    public User updateUserInfo(UpdateUserInfoDto updateUserInfoDto) {
        User user = getUserByUsername(updateUserInfoDto.getUsername());
        user.updateFrom(updateUserInfoDto);
        userMapper.updateById(user);
        return user;
    }

    @Override
    public PageResponse<UserInfoVo> getUser(String loginName, String trueName, Integer pageNo, Integer pageSize) {
        IPage<User> userPage = new Page<>(pageNo, pageSize);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("username", loginName);
        queryParams.put("true_name", trueName);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        setLikeWrapper(wrapper, queryParams);
        wrapper.orderByDesc("role_id", "create_date");
        wrapper.orderByAsc("status");

        userPage = userMapper.selectPage(userPage, wrapper);
        List<UserInfoVo> records = userPage.getRecords().stream().map(UserInfoVo::fromUser).collect(Collectors.toList());

        return PageResponse.<UserInfoVo>builder().data(records).total(userPage.getTotal()).build();
    }

    @Override
    public void handlerUser(Integer type, String userIds) {
        // 转换成数组 需要操作的用户的id数组
        String[] ids = userIds.split(",");
        switch (type) {
            case 1:
                updateUserStatus(ids, 1);
                break;
            case 2:
                updateUserStatus(ids, 2);
                break;
            case 3:
                for (String id : ids) {
                    userMapper.deleteById(Integer.parseInt(id));
                }
                break;
            default:
                throw new BusinessException(CommonErrorCode.E_100105);
        }
    }

    //    注册用户
    @Override
    public void addUser(AddUserDto addUserDto) {
        // 盐值
        String salt = UUID.randomUUID().toString().substring(0, 6);
        String newPwd = SaltEncryption.saltEncryption(addUserDto.getPassword(), salt);
        User user = addUserDto.toUser();
        System.out.println(addUserDto);
        if (addUserDto.getThreevalue().equals("部门经理")) {
            user.setPassword(newPwd);
            user.setSalt(salt);
            user.setRoleId(2);
            user.setCreateDate(new Date());
            userMapper.insert(user);
        }
        user.setPassword(newPwd);
        user.setSalt(salt);
        user.setCreateDate(new Date());
        userMapper.insert(user);
    }

    @Override
    public UserInfoVo getUserInfoById(Integer userId) {
        return fromUser(userMapper.selectById(userId));
    }

    @Override
    public List<UserInfoVo> getUserInfoByIds(List<Integer> userIds) {
        return userMapper.selectBatchIds(userIds).stream()
                .map(UserInfoVo::fromUser)
                .collect(Collectors.toList());
    }

    private void updateUserStatus(String[] ids, Integer status) {
        for (String id : ids) {
            // 当前需要修改的用户
            User user = userMapper.selectById(Integer.parseInt(id));
            user.setStatus(status);// 设置为启用的用户
            userMapper.updateById(user);
        }
    }
}
