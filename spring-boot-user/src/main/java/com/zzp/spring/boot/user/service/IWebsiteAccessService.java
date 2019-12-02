package com.zzp.spring.boot.user.service;

import com.zzp.spring.boot.user.model.WebsiteAccess;

import java.util.Date;

public interface IWebsiteAccessService {
    //根据访问时间得到访问的文章
    WebsiteAccess getByAccessDateIs(Date accessDate);
    //保存访问信息
    void save(WebsiteAccess websiteAccess);
}
