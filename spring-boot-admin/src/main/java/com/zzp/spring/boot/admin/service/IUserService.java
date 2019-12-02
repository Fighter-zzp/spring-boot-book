package com.zzp.spring.boot.admin.service;

import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.domain.User;
import org.springframework.data.domain.Page;

public interface IUserService {

    Page<User> findAllBySearch(Pages pages, Long userId, String username);

    User findUserByUserId(Long userId);

    String saveOrUpdateUser(User user);

    void updateUserIsEnable(Long userId, Integer isEnable);

    void deleteUser(Long userId);

    User findByUsername(String username);

}
