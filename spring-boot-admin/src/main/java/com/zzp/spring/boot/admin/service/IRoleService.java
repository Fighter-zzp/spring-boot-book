package com.zzp.spring.boot.admin.service;

import com.zzp.spring.boot.admin.domain.Role;

import java.util.List;

public interface IRoleService {

    Role findRoleByRoleId(Long roleId);

    List<Role> findAllByIsEnable();
}
