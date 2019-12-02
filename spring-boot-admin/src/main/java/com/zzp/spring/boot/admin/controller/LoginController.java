package com.zzp.spring.boot.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/login_error")
    public String login_error(Model model){
        model.addAttribute("login_error", "用户名或密码错误");
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage (Model model) {
        model.addAttribute("login_error", "注销成功！");
        return "login";
    }
}
