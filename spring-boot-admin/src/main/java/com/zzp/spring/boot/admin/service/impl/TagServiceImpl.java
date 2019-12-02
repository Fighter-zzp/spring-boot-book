package com.zzp.spring.boot.admin.service.impl;

import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.domain.Tag;
import com.zzp.spring.boot.admin.repository.TagRepository;
import com.zzp.spring.boot.admin.service.ITagService;
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

@Service
public class TagServiceImpl implements ITagService {
    @Resource
    private TagRepository tagRepository;

    @Override
    public Page<Tag> findAllBySearch(Pages pages, Long tagId, String tagName) {
        var pageAble = PageRequest.of(pages.getPage(), pages.getPageSize(), Sort.Direction.DESC, "tagId");
        return tagRepository.findAll(this.getWhereClause(tagId, tagName), pageAble);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteTagByTagId(Long tagId) {
        tagRepository.deleteById(tagId);
    }

    @Override
    public Tag findTagByTagName(String tagName) {
        return tagRepository.findByTagName(tagName);
    }

    @Override
    public Integer countByTagInputDate(Date tagInputdate) {
        return tagRepository.countByTagInputDate(tagInputdate);
    }

    @Override
    public Long count() {
        return tagRepository.count();
    }

    private Specification<Tag> getWhereClause(Long tagId, String tagName) {
        return (root, query, cb) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (tagId != null) {
                predicate.add(
                        cb.or(cb.equal(root.get("tagId"), tagId))
                );
            }
            if (!StringUtils.isEmpty(tagName)) {
                predicate.add(
                        cb.or(cb.like(root.get("tagName"), tagName + "%"))
                );
            }
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        };
    }
}
