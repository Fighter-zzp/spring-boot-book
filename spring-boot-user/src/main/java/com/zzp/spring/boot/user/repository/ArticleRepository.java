package com.zzp.spring.boot.user.repository;

import com.zzp.spring.boot.user.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    //展示已发布的页面
    Page<Article> findAllByIsEnable(Integer isEnable, Pageable pageable);
    //根据是否已发布和文章id查找
    Article findByIsEnableAndArticleId(Integer isEnable, Long articleId);
    //跟着文章是否顶置了来查找
    Page<Article> findAllByIsTopAndIsEnable(Integer isTop, Integer isEnable, Pageable pageable);
}
