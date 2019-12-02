package com.zzp.spring.boot.admin.service;

import com.zzp.spring.boot.admin.domain.Article;
import com.zzp.spring.boot.admin.domain.Pages;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface IArticleService {

    Page<Article> findAllBySearch(Pages pages, Long articleId, String articleName, String articleAuthors);

    Article findArticleByArticleId(Long articleId);

    void saveOrUpdateArticle(Article article);

    void updateArticleIsEnable(Long articleId, Integer isEnable);

    void deleteArticle(Long articleId);

    Integer countByArticleInputDate(Date articleInputDate);

    Long count();

}
