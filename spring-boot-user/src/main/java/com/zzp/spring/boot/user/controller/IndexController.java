package com.zzp.spring.boot.user.controller;

import com.zzp.spring.boot.user.model.Article;
import com.zzp.spring.boot.user.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private IArticleService articleService;

    @GetMapping({"/","index"})
    public String index(Model model){
        var isTopArticleList = articleService.findIsTopArticleList();
        model.addAttribute("articleList", isTopArticleList);
        return "index";
    }
}
