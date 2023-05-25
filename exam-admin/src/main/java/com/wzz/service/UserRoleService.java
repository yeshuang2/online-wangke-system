package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.UserRole;

import java.util.List;

/**
 * @author by ysg
 * @implNote 2022/10/20 19:49
 */
public interface UserRoleService extends IService<UserRole> {

    String getMenuInfo(Integer roleId);

    List<UserRole> getUserRole();
}
