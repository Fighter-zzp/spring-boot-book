package com.zzp.spring.boot.user.controller;


import com.zzp.spring.boot.user.model.Tag;
import com.zzp.spring.boot.user.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private ITagService tagService;

    @GetMapping("/")
    public String tag(Model model) {
        model.addAttribute("tagList", tagService.findAll());
        return "tag";
    }

    //根据标签名找文章
    @GetMapping("/{tagName}")
    public String tag(Model model, @PathVariable String tagName) {
        Tag tag = tagService.findByTagName(tagName);
        model.addAttribute("tag", tag);
        model.addAttribute("articleList", tag.getArticleList());
        return "tagArticle";
    }
}
