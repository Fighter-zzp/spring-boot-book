package com.zzp.spring.boot.user.service.impl;

import com.zzp.spring.boot.user.model.WebsiteAccess;
import com.zzp.spring.boot.user.repository.WebSiteAccessRepository;
import com.zzp.spring.boot.user.service.IWebsiteAccessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
@Service
public class WebsiteAccessServiceImpl implements IWebsiteAccessService {
    @Resource
    private WebSiteAccessRepository webSiteAccessRepository;

    @Override
    public WebsiteAccess getByAccessDateIs(Date accessDate) {
        return webSiteAccessRepository.getByAccessDateIs(accessDate);
    }

    @Override
    public void save(WebsiteAccess websiteAccess) {
        webSiteAccessRepository.save(websiteAccess);
    }
}
