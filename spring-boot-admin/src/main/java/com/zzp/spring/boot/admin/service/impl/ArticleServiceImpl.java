package com.zzp.spring.boot.admin.service.impl;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Article;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.domain.Tag;
import com.zzp.spring.boot.admin.repository.ArticleRepository;
import com.zzp.spring.boot.admin.repository.TagRepository;
import com.zzp.spring.boot.admin.service.IArticleService;
import com.zzp.spring.boot.admin.service.ITagService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private ITagService tagService;

    @Override
    public Article findArticleByArticleId(Long articleId) {
        var optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isEmpty()) {
            return null;
        }
        Article article = optionalArticle.get();
        var tagList = article.getTagList();
        if (CollectionUtils.isNotEmpty(tagList)) {
            article.setTagsStr(StringUtils.join(tagList.stream().map(Tag::getTagName).toArray(), ","));
        }
        return article;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveOrUpdateArticle(Article article) {
        String tagsStr = article.getTagsStr();
        var tagList = new ArrayList<Tag>();
        if (StringUtils.isNotBlank(tagsStr)) {
            String[] tagNames = tagsStr.split(",");
            for (String tagName : tagNames) {
                Tag tag = tagService.findTagByTagName(tagName);
                if (tag == null) {
                    tag = new Tag(tagName);
                    tag.setTagInputDate(new Date());
                }
                tag = tagService.saveTag(tag);
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        if (article.getArticleId() == null) {
            article.setIsEnable(Constants.NO);
            article.setArticleInputDate(new Date());
            article.setArticleInputUser(1L);
            article.setArticleReadingTime(0);
        }
        articleRepository.save(article);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateArticleIsEnable(Long articleId, Integer isEnable) {
        var optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setIsEnable(isEnable);
            articleRepository.save(article);
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    @Override
    public Page<Article> findAllBySearch(Pages pages, Long articleId, String articleName, String articleAuthors) {
        var pageable = PageRequest.of(pages.getPage(), pages.getPageSize(), Sort.Direction.DESC, "articleId");
        return articleRepository.findAll(this.getWhereClause(articleId, articleName, articleAuthors), pageable);
    }

    @Override
    public Integer countByArticleInputDate(Date articleInputDate) {
        return articleRepository.countByArticleInputDate(articleInputDate);
    }

    @Override
    public Long count() {
        return articleRepository.count();
    }

    private Specification<Article> getWhereClause(Long articleId, String articleName, String articleAuthors) {
        return (root,query,cb)->{
            var predicate = new ArrayList<Predicate>();
            if (articleId != null) {
                predicate.add(
                        cb.or(cb.equal(root.get("articleId"), articleId))
                );
            }
            if (!StringUtils.isEmpty(articleName)) {
                predicate.add(
                        cb.or(cb.like(root.get("articleName"), articleName + "%"))
                );
            }
            if (!StringUtils.isEmpty(articleAuthors)) {
                predicate.add(
                        cb.or(cb.like(root.get("articleAuthors"), articleAuthors + "%"))
                );
            }
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        };
    }
}
