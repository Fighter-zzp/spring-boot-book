package com.zzp.spring.boot.user.controller;

import com.zzp.spring.boot.user.constants.Constants;
import com.zzp.spring.boot.user.model.Article;
import com.zzp.spring.boot.user.service.IArticleService;
import com.zzp.spring.boot.user.util.HtmlSpirit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogController {
    @Autowired
    private IArticleService articleService;

    //主页，默认1
    @GetMapping("/blog")
    public String blog(Model model) {
        return this.blog(model, 1);
    }

    // 主页面显示，根据分页
    @GetMapping("/blog/{pageNumber}")
    public String blog(Model model, @PathVariable Integer pageNumber){
        if (pageNumber==null) pageNumber=1;
        var articlePage = articleService.findBlogArticleList((pageNumber - 1)
                * Constants.defaultPageSize, Constants.defaultPageSize);
        var articleList = articlePage.getContent();
        articleList.forEach(article -> {
            String articleIntroduction = HtmlSpirit.delHTMLTag(article.getArticleContent());//对文章内容进行html正则比较
            //限制文章介绍的长度
            article.setArticleIntroduction(articleIntroduction.length() > 100 ? articleIntroduction.substring(0, 100) : articleIntroduction);
        });
        model.addAttribute("articleList", articleList);
        model.addAttribute("totalCount", articlePage.getTotalElements());
        model.addAttribute("pageNumber", pageNumber);
        return "blog";
    }



}
