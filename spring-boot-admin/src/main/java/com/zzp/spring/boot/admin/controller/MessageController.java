package com.zzp.spring.boot.admin.controller;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Message;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private IMessageService messageService;

    @GetMapping("/list")
    public String message(Integer pageNumber, String name, String email, Model model) {
        var messagePage = messageService.findAllMessageBySearch(Pages.defaultPages(pageNumber), name, email);
        model.addAttribute("messageList", messagePage.getContent());
        model.addAttribute("totalCount", messagePage.getTotalElements());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("menuFlag", Constants.MESSAGE_MENU_FLAG);
        return "message/index";
    }

    @GetMapping("/messageInfo")
    public String messageInfo(Long messageId, Model model) {
        Message message = messageService.findMessageByMessageId(messageId);
        if(message.getIsRead() == 0){
            messageService.updateMessageIsRead(messageId);
        }
        model.addAttribute("message", message);
        model.addAttribute("menuFlag", Constants.MESSAGE_MENU_FLAG);
        return "message/edit";
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteMessage(@RequestParam Long messageId) {
        messageService.deleteMessage(messageId);
    }
}
