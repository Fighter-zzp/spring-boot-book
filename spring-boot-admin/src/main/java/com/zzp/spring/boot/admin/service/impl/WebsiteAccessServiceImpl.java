package com.zzp.spring.boot.admin.service.impl;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.WebsiteAccess;
import com.zzp.spring.boot.admin.repository.WebsiteAccessRepository;
import com.zzp.spring.boot.admin.service.IWebsiteAccessService;
import com.zzp.spring.boot.admin.until.DateUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
@Service
public class WebsiteAccessServiceImpl implements IWebsiteAccessService {
    @Resource
    private WebsiteAccessRepository websiteAccessRepository;

    @Resource
    private EntityManager entityManager;

    @Override
    public WebsiteAccess getByAccessDateIs(Date accessDate) {
        return websiteAccessRepository.getByAccessDateIs(accessDate);
    }

    @Override
    public void save(WebsiteAccess websiteAccess) {
        websiteAccessRepository.save(websiteAccess);
    }

    @Override
    public Integer sumWebsiteAccess(Date date, Integer days) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Integer.class);
        var root = query.from(WebsiteAccess.class);
        query.select(criteriaBuilder.sum(root.get("accessCount")));
        if (days == null && date != null) {
            var predicate = criteriaBuilder.equal(root.get("accessDate"), date);
            query.where(predicate);
        } else if (date != null) {
            var predicate = criteriaBuilder.between(root.get("accessDate"), DateUtils.getDateBefore(date, days), date);
            query.where(predicate);
        }
        Integer result = entityManager.createQuery(query).getSingleResult();
        if (result == null) {
            result = 0;
        }
        return result;
    }

    @Override
    public List<WebsiteAccess> findChartsWebsiteAccess() {
        var pageable = PageRequest.of(Constants.startPage, Constants.defaultPageSize, Sort.Direction.ASC, "accessDate");
        var websiteAccessPage = websiteAccessRepository.findAll(pageable);
        return websiteAccessPage.getContent();
    }
}
