package com.zzp.spring.boot.admin.controller;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.domain.User;
import com.zzp.spring.boot.admin.service.IRoleService;
import com.zzp.spring.boot.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping("/list")
    public String user(Integer pageNumber, Long userId, String username, Model model) {
        var userPage = userService.findAllBySearch(Pages.defaultPages(pageNumber), userId, username);
        model.addAttribute("userList", userPage.getContent());
        model.addAttribute("totalCount", userPage.getTotalElements());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("menuFlag", Constants.USER_MENU_FLAG);
        return "user/index";
    }

    @GetMapping("/saveOrUpdatePage")
    public String editUser(Model model, Long userId) {
        var roleList = roleService.findAllByIsEnable();
        var user = new User();
        if (userId != null) {
            user = userService.findUserByUserId(userId);
            List userRoleList = user.getRoleList();
            for (var role : roleList) {
                if (userRoleList.contains(role)) {
                    role.setIsHave(Constants.YES);
                }
            }
        } else {
            user.setUserId(0L);
        }
        model.addAttribute("user", user);
        model.addAttribute("menuFlag", Constants.USER_MENU_FLAG);
        model.addAttribute("roleList", roleList);
        return "user/edit";
    }

    @PostMapping("/updateUserIsEnable")
    @ResponseBody
    public void updateUserIsEnable(Long userId, Integer isEnable) {
        userService.updateUserIsEnable(userId, isEnable);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteArticle(Long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public void saveOrUpdateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

}

