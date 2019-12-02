package com.zzp.spring.boot.admin.controller;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.WebsiteAccess;
import com.zzp.spring.boot.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private ILinkService linkService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IWebsiteAccessService websiteAccessService;

    @RequestMapping({"/", "/index"})

    public String index(Model model) {
        model.addAttribute("articleTotalCount", articleService.count());
        model.addAttribute("tagTotalCount", tagService.count());
        model.addAttribute("linkTotalCount", linkService.count());
        model.addAttribute("messageTotalCount", messageService.count());
        model.addAttribute("todayWebsiteVisit", websiteAccessService.sumWebsiteAccess(new Date(), null));
        model.addAttribute("weekWebsiteVisit", websiteAccessService.sumWebsiteAccess(new Date(), 7));
        model.addAttribute("monthWebsiteVisit", websiteAccessService.sumWebsiteAccess(new Date(), 30));
        model.addAttribute("totalWebsiteVisit", websiteAccessService.sumWebsiteAccess(null, null));
        var websiteAccessList = websiteAccessService.findChartsWebsiteAccess();
        var websiteAccessCountList = websiteAccessList.stream().map(WebsiteAccess::getAccessCount).collect(Collectors.toList());
        var websiteAccessDateList = websiteAccessList.stream().map(WebsiteAccess::getAccessDate).collect(Collectors.toList());
        model.addAttribute("websiteAccessCountList", websiteAccessCountList);
        model.addAttribute("websiteAccessDateList", websiteAccessDateList);
        model.addAttribute("menuFlag", Constants.INDEX_MENU_FLAG);
        return "index";
    }
}
