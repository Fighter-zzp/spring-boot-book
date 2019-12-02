package com.zzp.spring.boot.admin.controller;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.WebsiteConfig;
import com.zzp.spring.boot.admin.service.IWebsiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/system")
public class WebsiteConfigController {

    @Autowired
    private IWebsiteConfigService websiteConfigService;

    @GetMapping("/edit")
    public String system(Model model) {
        model.addAttribute("websiteConfig", websiteConfigService.findWebsiteConfig());
        model.addAttribute("menuFlag", Constants.SYSTEM_MENU_FLAG);
        return "system/edit";
    }

    @PostMapping("/saveOrUpdayeWebsiteConfig")
    @ResponseBody
    public void saveOrUpdateWebsiteConfig(@RequestBody WebsiteConfig websiteConfig) {
        websiteConfigService.saveWebsiteConfig(websiteConfig);
    }
}
