package com.zzp.spring.boot.user.service.impl;

import com.zzp.spring.boot.user.model.Message;
import com.zzp.spring.boot.user.repository.MessageRepository;
import com.zzp.spring.boot.user.service.IMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class MessageServiceImpl implements IMessageService {
    @Resource
    private MessageRepository messageRepository;

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
