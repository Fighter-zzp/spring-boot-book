package com.zzp.spring.boot.user.controller;

import com.zzp.spring.boot.user.constants.Constants;
import com.zzp.spring.boot.user.model.Article;
import com.zzp.spring.boot.user.service.IArticleService;
import com.zzp.spring.boot.user.util.HtmlSpirit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private IArticleService articleService;

    @GetMapping("/search")
    public String search(Model model, Integer pageNumber, String keyword) {
        if (StringUtils.isEmpty(keyword))
            return "search";
        else if (pageNumber == null)
            pageNumber = 1;
        // 查找查询到的文章
        var searchArticleList = articleService.findSearchArticleList((pageNumber - 1) * Constants.defaultPageSize, Constants.defaultPageSize, keyword);
        var articleList = searchArticleList.getContent();
        articleList.forEach(article -> {
            var articleIntroduction = HtmlSpirit.delHTMLTag(article.getArticleContent());
            //文章介绍字数不超过100
            article.setArticleIntroduction(articleIntroduction.length() > 100 ? articleIntroduction.substring(0, 100) : articleIntroduction);
        });
        model.addAttribute("articleList", articleList);
        model.addAttribute("totalCount", searchArticleList.getTotalElements());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("keyword", keyword);
        return "search";
    }
}
