package com.zzp.spring.boot.user.controller;

import com.zzp.spring.boot.user.model.Article;
import com.zzp.spring.boot.user.service.IArticleService;
import com.zzp.spring.boot.user.util.MarkdownToHtml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private IArticleService articleService;

    @GetMapping("/article/{id}")
    public String viewArticle(Model model, @PathVariable Long id){
        var article = articleService.findIsEnableArticleByArticleId(id);
        article.setArticleReadingTime(article.getArticleReadingTime()+1);
        articleService.saveArticle(article);
        article.setArticleShowContent(MarkdownToHtml.markDownToHtml(article.getArticleContent()));
        log.info(article.toString());
        log.info(article.getTagList().toString());
        model.addAttribute("article", article);
        return "article";
    }
}
