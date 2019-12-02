package com.zzp.spring.boot.admin.service;

import com.zzp.spring.boot.admin.domain.WebsiteConfig;

public interface IWebsiteConfigService {

    WebsiteConfig findWebsiteConfig();

    void saveWebsiteConfig(WebsiteConfig websiteConfig);
}
