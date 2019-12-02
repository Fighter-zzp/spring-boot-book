package com.zzp.spring.boot.user.controller;

import com.zzp.spring.boot.user.model.Message;
import com.zzp.spring.boot.user.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private IMessageService messageService;

    @GetMapping("/")
    public String contact(){ return "contact";}

    @PostMapping("/sendMail")
    @ResponseBody
    @Transactional(rollbackFor = Throwable.class)
    public String sendMail(@RequestBody Message message){
        message.setMessageInputDate(new Date());
        messageService.saveMessage(message);
        return "success";
    }
}
