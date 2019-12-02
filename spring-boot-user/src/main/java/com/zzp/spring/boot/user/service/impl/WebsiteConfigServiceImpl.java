package com.zzp.spring.boot.user.service.impl;

import com.zzp.spring.boot.user.model.WebsiteConfig;
import com.zzp.spring.boot.user.repository.WebsiteConfigRepository;
import com.zzp.spring.boot.user.service.IWebsiteConfigService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class WebsiteConfigServiceImpl implements IWebsiteConfigService {
    @Resource
    private WebsiteConfigRepository websiteConfigRepository;

    @Override
    @Cacheable(value = "websiteConfig", key = "#page")
    public WebsiteConfig findWebsiteConfig() {
        var websiteConfig = websiteConfigRepository.findById(1L);
        return websiteConfig.orElse(null);
    }

    @Override
    public void saveWebsiteConfig(WebsiteConfig websiteConfig) {
        websiteConfigRepository.save(websiteConfig);
    }
}
