package com.zzp.spring.boot.user.interceptors;

import com.zzp.spring.boot.user.model.WebsiteAccess;
import com.zzp.spring.boot.user.service.ILinkService;
import com.zzp.spring.boot.user.service.ITagService;
import com.zzp.spring.boot.user.service.IWebsiteAccessService;
import com.zzp.spring.boot.user.service.IWebsiteConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//拦截器
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    @Autowired
    private IWebsiteAccessService websiteAccessService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ILinkService linkService;
    @Autowired
    private IWebsiteConfigService websiteConfigService;

    /**
     * 拦截器 在渲染时启动方法，这里是添加网页访问次数和底部信息动态加载
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            ModelMap modelMap = modelAndView.getModelMap();
            logger.info("正在更新网站访问次数。");
            WebsiteAccess websiteAccess = websiteAccessService.getByAccessDateIs(new Date());
            websiteAccess.setAccessCount(websiteAccess.getAccessCount() + 1);
            websiteAccessService.save(websiteAccess);
            logger.info("加入底部数据。");
            //标签列表
            modelMap.addAttribute("tagList", tagService.findAll());
            //友情链接列表
            modelMap.addAttribute("linkList", linkService.findAllByIsEnable());
            //友情链接列表
            modelMap.addAttribute("websiteConfig", websiteConfigService.findWebsiteConfig());
        }
    }
}
