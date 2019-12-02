package com.zzp.spring.boot.admin.interceptor;


import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private IWebsiteConfigService websiteConfigService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IUserService userService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            ModelMap modelMap = modelAndView.getModelMap();
            Date date = new Date();
            //加载数据
            Authentication auth = authenticationService.getAuthentication();
            if (auth != null) {
                String username = auth.getName();
                modelMap.addAttribute("username", username);
                var user = userService.findByUsername(username);
                if(user != null){
                    //赋值用户所拥有菜单集合，动态渲染菜单
                    modelMap.addAttribute("userRoleList", Constants.getUserRoleList(user.getRoleList()));
                }
            }
            Integer messageCount = messageService.countByIsRead(Constants.NO);
            modelMap.addAttribute("messageCount", messageCount);
            Integer tagCount = tagService.countByTagInputDate(date);
            modelMap.addAttribute("tagCount", tagCount);
            Integer articleCount = articleService.countByArticleInputDate(date);
            modelMap.addAttribute("articleCount", articleCount);
            modelMap.addAttribute("sumCount", articleCount + tagCount + messageCount);
            var messageList = messageService.findAllByIsRead(Constants.NO);
            modelMap.addAttribute("mainbarMessageList", messageList);
            modelMap.addAttribute("mainbarMessageListCount", messageList.size());
            modelMap.addAttribute("websiteConfig", websiteConfigService.findWebsiteConfig());

        }
    }
}
