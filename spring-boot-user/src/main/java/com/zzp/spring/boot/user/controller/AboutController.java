package com.zzp.spring.boot.user.controller;

import com.zzp.spring.boot.user.model.WebsiteConfig;
import com.zzp.spring.boot.user.service.IArticleService;
import com.zzp.spring.boot.user.service.IWebsiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
    @Autowired
    private IArticleService articleService;

    @Autowired
    private IWebsiteConfigService websiteConfigService;

    // 得到关于我们的信息
    @GetMapping("/about")
    public String about(Model model){
        WebsiteConfig websiteConfig = websiteConfigService.findWebsiteConfig();
        model.addAttribute("article", articleService.findArticleByArticleId(websiteConfig.getAboutPageArticleId()));
        return "about";
    }
}
