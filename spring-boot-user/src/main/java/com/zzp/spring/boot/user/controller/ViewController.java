package com.springboot.controller;

import com.zzp.spring.boot.user.model.Article;
import com.zzp.spring.boot.user.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @Autowired
    private IArticleService articleService;

    /**
     * 查看单个文章
     * @param model 试图渲染模型
     * @param id 文章id
     * @return 文章
     */
    @GetMapping("/view/{id}")
    public String viewArticle(Model model, @PathVariable Long id) {
        var articleInfo = articleService.findArticleByArticleId(id);
        model.addAttribute("articleInfo", articleInfo);
        return "article";
    }
}
