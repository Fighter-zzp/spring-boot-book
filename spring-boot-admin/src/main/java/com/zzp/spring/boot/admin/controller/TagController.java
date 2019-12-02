package com.zzp.spring.boot.admin.controller;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private ITagService tagService;

    @GetMapping("/list")
    public String tag(Integer pageNumber, Long tagId, String tagName, Model model) {
        var tagPage = tagService.findAllBySearch(Pages.defaultPages(pageNumber),
                tagId, tagName);
        model.addAttribute("tagList", tagPage.getContent());
        model.addAttribute("totalCount", tagPage.getTotalElements());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("tagId", tagId);
        model.addAttribute("tagName", tagName);
        model.addAttribute("menuFlag", Constants.TAG_MENU_FLAG);
        return "tag/index";
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteTag(@RequestParam Long tagId) {
        tagService.deleteTagByTagId(tagId);
    }
}
