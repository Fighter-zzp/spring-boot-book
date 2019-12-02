package com.zzp.spring.boot.user.service.impl;

import com.zzp.spring.boot.user.constants.Constants;
import com.zzp.spring.boot.user.model.Article;
import com.zzp.spring.boot.user.repository.ArticleRepository;
import com.zzp.spring.boot.user.service.IArticleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private ArticleRepository articleRepository;

    // 找顶置的文章
    @Override
    @Cacheable(value = "indexPageArticleList")
    public List<Article> findIsTopArticleList() {
        var pageAble = PageRequest.of(0, 6, Sort.Direction.DESC, "articleId");
        var page = articleRepository.findAllByIsTopAndIsEnable(Constants.YES, Constants.YES, pageAble);
        var articleList = page.getContent();
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            article.setImageNo(i + 1);
        }
        return articleList;
    }

    /**
     * 已发布的文章展示分页
     *
     * @param page 页码
     * @param size 内容数
     * @return 文章
     */
    @Override
    @Cacheable(value = "blogArticle", key = "#page")
    public Page<Article> findBlogArticleList(int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.Direction.DESC, "articleId");
        return articleRepository.findAllByIsEnable(Constants.YES, pageable);
    }

    // 这里是找文章的详情
    @Override
    public Page<Article> findSearchArticleList(int page, int size, String keyword) {
        var pageAble = PageRequest.of(page, size, Sort.Direction.DESC, "articleId");
        return articleRepository.findAll(this.getWhereClause(keyword),pageAble);
    }

    @Override
    public Article findArticleByArticleId(Long articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }

    @Override
    public Article findIsEnableArticleByArticleId(Long articleId) {
        return articleRepository.findByIsEnableAndArticleId(Constants.YES,articleId);
    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    /**
     * 使用动态sql，这里用来文章搜索
     * @param keyword articleName或者articleContent的关键词
     * @return 搜索后的文章
     */
    private Specification<Article> getWhereClause(String keyword) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            var predicate = new ArrayList<Object>();
            if (StringUtils.isNotBlank(keyword)) {
                predicate.add(
                        criteriaBuilder.or(
                                criteriaBuilder.like(root.get("articleName"), "%" + keyword + "%"),
                                criteriaBuilder.like(root.get("articleContent"), "%" + keyword + "%")
                        )
                );
            }
            predicate.add(criteriaBuilder.equal(root.get("isEnable"), Constants.YES));
            var pre = new Predicate[predicate.size()];
            return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
        };
    }
}
