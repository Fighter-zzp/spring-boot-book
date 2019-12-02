package com.zzp.spring.boot.admin.service.impl;

import com.zzp.spring.boot.admin.domain.Link;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.repository.LinkRepository;
import com.zzp.spring.boot.admin.service.ILinkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LinkServiceImpl implements ILinkService {
    @Resource
    private LinkRepository linkRepository;

    @Override
    public Page<Link> findAllBySearch(Pages pages, Long linkId, String linkName) {
        var pageAbel = PageRequest.of(pages.getPage(), pages.getPageSize(), Sort.Direction.DESC,"linkId");
        return linkRepository.findAll(this.getWhereClause(linkId, linkName), pageAbel);
    }

    private Specification<Link> getWhereClause(Long linkId, String linkName) {
        return (root, query,cb) ->{
            List<Predicate> predicate = new ArrayList<>();
            if (linkId != null) {
                predicate.add(
                        cb.or(cb.equal(root.get("linkId"), linkId))
                );
            }
            if (!StringUtils.isEmpty(linkName)) {
                predicate.add(
                        cb.or(cb.like(root.get("linkName"), linkName + "%"))
                );
            }
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        };
    }

    @Override
    public Link findLinkByLinkId(Long linkId) {
        var link = linkRepository.findById(linkId);
        return link.orElse(null);
    }

    @Override
    public void saveOrUpdateLink(Link link) {
        linkRepository.save(link);
    }

    @Override
    public void deleteLink(Long linkId) {
        linkRepository.deleteById(linkId);
    }

    @Override
    public Long count() {
        return linkRepository.count();
    }
}
