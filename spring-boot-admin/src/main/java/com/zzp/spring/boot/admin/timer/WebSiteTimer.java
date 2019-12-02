package com.zzp.spring.boot.admin.timer;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.service.IMessageService;
import com.zzp.spring.boot.admin.service.IWebsiteAccessService;
import com.zzp.spring.boot.admin.service.IWebsiteConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WebSiteTimer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IWebsiteConfigService websiteConfigService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private IWebsiteAccessService websiteAccessService;
    @Autowired
    private IMessageService messageService;
    @Scheduled(cron = "0 0 0 1/1 * ?")
    private void sendDailyData() {
        String subject = "博客每日数据";
        String text = this.initData();
        var websiteConfig = websiteConfigService.findWebsiteConfig();
        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(websiteConfig.getEmailUsername());
        simpleMailMessage.setTo(websiteConfig.getEmailUsername());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        try {
            javaMailSender.send(simpleMailMessage);
            logger.info("发送博客每日数据成功！");
        } catch (Exception e) {
            logger.error("发送博客每日数据异常！", e);
        }
    }

    private String initData(){
        StringBuilder sb = new StringBuilder();
        var sdf=new SimpleDateFormat("yyyy-MM-dd");
        var websiteAccess = websiteAccessService.getByAccessDateIs(new Date());
        sb.append("日期是：");
        sb.append(sdf.format(websiteAccess.getAccessDate()));
        sb.append("访问量为：");
        sb.append(websiteAccess.getAccessCount());
        sb.append("未读消息有：");
        int count = messageService.countByIsRead(Constants.YES);
        sb.append(count);
        sb.append("条");
        return sb.toString();
    }
}
