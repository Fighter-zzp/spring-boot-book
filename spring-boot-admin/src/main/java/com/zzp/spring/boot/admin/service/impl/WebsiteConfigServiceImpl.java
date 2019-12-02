package com.zzp.spring.boot.admin.service.impl;

import com.zzp.spring.boot.admin.domain.WebsiteConfig;
import com.zzp.spring.boot.admin.repository.WebsiteConfigRepository;
import com.zzp.spring.boot.admin.service.IWebsiteConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WebsiteConfigServiceImpl implements IWebsiteConfigService {
    @Resource
    private WebsiteConfigRepository websiteConfigRepository;

    @Override
    public WebsiteConfig findWebsiteConfig() {
        var websiteConfigOptional = websiteConfigRepository.findById(1L);
        return websiteConfigOptional.orElse(null);
    }

    @Override
    public void saveWebsiteConfig(WebsiteConfig websiteConfig) {
        websiteConfigRepository.save(websiteConfig);
    }
}
