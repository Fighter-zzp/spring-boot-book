package com.zzp.spring.boot.admin.service.impl;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Role;
import com.zzp.spring.boot.admin.repository.RoleRepository;
import com.zzp.spring.boot.admin.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByRoleId(Long roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        return optionalRole.orElse(null);
    }

    @Override
    public List<Role> findAllByIsEnable() {
        return roleRepository.findAllByIsEnable(Constants.YES);
    }
}
