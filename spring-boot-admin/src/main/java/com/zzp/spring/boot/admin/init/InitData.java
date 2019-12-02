package com.zzp.spring.boot.admin.init;


import com.zzp.spring.boot.admin.domain.Role;
import com.zzp.spring.boot.admin.domain.User;
import com.zzp.spring.boot.admin.domain.WebsiteConfig;
import com.zzp.spring.boot.admin.repository.RoleRepository;
import com.zzp.spring.boot.admin.service.IUserService;
import com.zzp.spring.boot.admin.service.IWebsiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class InitData {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IWebsiteConfigService websiteConfigService;

    @PostConstruct
    private void initRoleData() {
        var role1 = new Role(1L, "ROLE_USER", 1);
        var role2 = new Role(2L, "ROLE_ADMIN", 1);
        var role3 = new Role(3L, "ROLE_SUPERADMIN", 1);
        var role4 = new Role(4L, "ROLE_SYSTEMADMIN", 1);
        var roleList = new ArrayList<Role>();
        roleList.add(role1);
        roleList.add(role2);
        roleList.add(role3);
        roleList.add(role4);
        roleRepository.saveAll(roleList);
        var user = userService.findUserByUserId(1L);
        if (user == null) {
            userService.saveOrUpdateUser(new User(1L, "admin", "123", "admin@springboot.cn", 1, roleList));
        }
        var websiteConfig = websiteConfigService.findWebsiteConfig();
        if (websiteConfig == null) {
            websiteConfigService.saveWebsiteConfig(new WebsiteConfig(1L, "SpringBoot博客", "dalaoyang", 7L, "辽ICP备17014944号-1", "Dalaoyang.cn", "dalaoyang@aliyun.com"));
        }
    }
}
