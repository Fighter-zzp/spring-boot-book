package com.zzp.spring.boot.user.timer;

import com.zzp.spring.boot.user.model.WebsiteAccess;
import com.zzp.spring.boot.user.service.IWebsiteAccessService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 设计时间定时器，定时插入访问记录
 */
@Component
public class WebSiteTimer {
    @Resource
    private IWebsiteAccessService websiteAccessService;

    @Scheduled(cron = "0 0 0 1/1 * ?")
    private void updateTodayWebSiteVisits(){
        var websiteAccess = new WebsiteAccess();
        websiteAccess.setAccessDate(new Date());
        websiteAccess.setAccessCount(1);
        websiteAccessService.save(websiteAccess);
    }
}
