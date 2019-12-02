package com.zzp.spring.boot.user.service;

import com.zzp.spring.boot.user.model.WebsiteConfig;

public interface IWebsiteConfigService {
    WebsiteConfig findWebsiteConfig();

    void saveWebsiteConfig(WebsiteConfig websiteConfig);
}
