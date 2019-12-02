package com.zzp.spring.boot.user.init;


import com.zzp.spring.boot.user.model.WebsiteAccess;
import com.zzp.spring.boot.user.model.WebsiteConfig;
import com.zzp.spring.boot.user.service.IWebsiteAccessService;
import com.zzp.spring.boot.user.service.IWebsiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
// 初始化配置和访问
@Component
public class InitWebsiteData {
    @Autowired
    private IWebsiteAccessService websiteAccessService;

    @Autowired
    private IWebsiteConfigService websiteConfigService;

    @PostConstruct
    public void initWebsiteVisits(){
        //查询当日是否存在博客访问表记录，不存在则插入
        if(websiteAccessService.getByAccessDateIs(new Date()) == null){
            WebsiteAccess websiteAccess = new WebsiteAccess();
            websiteAccess.setAccessCount(1);
            websiteAccess.setAccessDate(new Date());
            websiteAccessService.save(websiteAccess);
        }
        //查询当日是否存在博客配置表记录，不存在则插入
        if(websiteConfigService.findWebsiteConfig()==null){
            WebsiteConfig websiteConfig = new WebsiteConfig();
            websiteConfig.setId(1L);
            websiteConfig.setAboutPageArticleId(7L);
            websiteConfig.setBlogName("zzp个人博客");
            websiteConfig.setAuthorName("zzp");
            websiteConfig.setDomainName("smtp.aliyun.com");
            websiteConfig.setRecordNumber("粤ICP备709394号-1");
            websiteConfig.setEmailUsername("zzpTest@163.com");
            websiteConfigService.saveWebsiteConfig(websiteConfig);
        }
    }
}
