package com.zzp.spring.boot.admin.controller;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Article;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("/list")
    public String article(Integer pageNumber, Long articleId, String articleName,
                          String articleAuthors, Model model) {
        var pages = Pages.defaultPages(pageNumber);
        var articlePage = articleService.findAllBySearch(pages, articleId, articleName, articleAuthors);
        model.addAttribute("articleList", articlePage.getContent());
        model.addAttribute("totalCount", articlePage.getTotalElements());
        model.addAttribute("pageNumber", pages.getPageNumber());
        model.addAttribute("articleName", articleName);
        model.addAttribute("articleAuthors", articleAuthors);
        model.addAttribute("articleId", articleId);
        model.addAttribute("menuFlag", Constants.ARTICLE_MENU_FLAG);
        return "article/index";
    }

    @GetMapping("/saveOrUpdatePage")
    public String saveOrUpdateArticlePage(Model model, Long articleId) {
        var article = new Article();
        if (articleId != null) {
            article = articleService.findArticleByArticleId(articleId);
        } else {
            article.setArticleId(0L);
        }
        model.addAttribute("article", article);
        model.addAttribute("menuFlag", Constants.ARTICLE_MENU_FLAG);
        return "article/edit";
    }

    @PostMapping("/updateArticleIsEnable")
    @ResponseBody
    public void updateArticleIsEnable(@RequestParam Long articleId, @RequestParam Integer isEnable) {
        articleService.updateArticleIsEnable(articleId, isEnable);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteArticle(@RequestParam Long articleId) {
        articleService.deleteArticle(articleId);
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdateArticle(@RequestBody Article article) {
        articleService.saveOrUpdateArticle(article);
        return "success";
    }
}
