package com.zzp.spring.boot.admin.service;

import com.zzp.spring.boot.admin.domain.WebsiteAccess;

import java.util.Date;
import java.util.List;

public interface IWebsiteAccessService {

    WebsiteAccess getByAccessDateIs(Date accessDate);

    void save(WebsiteAccess websiteAccess);

    Integer sumWebsiteAccess(Date date, Integer days);

    List<WebsiteAccess> findChartsWebsiteAccess();
}
