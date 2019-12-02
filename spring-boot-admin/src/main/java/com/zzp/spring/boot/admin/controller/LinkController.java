package com.zzp.spring.boot.admin.controller;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Link;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private ILinkService linkService;
    @GetMapping("/list")
    public String link(Integer pageNumber, Long linkId, String linkName, Model model) {
        var linkPage = linkService.findAllBySearch(Pages.defaultPages(pageNumber), linkId, linkName);
        model.addAttribute("linkList", linkPage.getContent());
        model.addAttribute("totalCount", linkPage.getTotalElements());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("linkId", linkId);
        model.addAttribute("linkName", linkName);
        model.addAttribute("menuFlag", Constants.LINK_MENU_FLAG);
        return "link/index";
    }

    @GetMapping("/saveOrUpdatePage")
    public String saveOrUpdateLinkPage(Long linkId, Model model) {
        var link = new Link();
        if (linkId != null) {
            link = linkService.findLinkByLinkId(linkId);
        } else {
            link.setLinkId(0L);
        }
        model.addAttribute("link", link);
        model.addAttribute("menuFlag", Constants.LINK_MENU_FLAG);
        return "link/edit";
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public void saveOrUpdateLink(@RequestBody Link link) {
        linkService.saveOrUpdateLink(link);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteLink(Long linkId) {
        linkService.deleteLink(linkId);
    }
}
