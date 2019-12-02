package com.zzp.spring.boot.admin.repository;


import com.zzp.spring.boot.admin.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    Integer countByArticleInputDate(Date articleInputDate);

}
