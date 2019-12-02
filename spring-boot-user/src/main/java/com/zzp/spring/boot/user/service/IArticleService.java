package com.zzp.spring.boot.user.service;

import com.zzp.spring.boot.user.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IArticleService {
    List<Article> findIsTopArticleList();

    //已发布的文章展示分页
    Page<Article> findBlogArticleList(int page, int size);

    Page<Article> findSearchArticleList(int page, int size, String keyword);

    Article findArticleByArticleId(Long articleId);
    //由文章id找文章，已发布的
    Article findIsEnableArticleByArticleId(Long articleId);

    void saveArticle(Article article);

}
