package com.zzp.spring.boot.admin.service;

import com.zzp.spring.boot.admin.domain.Message;
import com.zzp.spring.boot.admin.domain.Pages;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMessageService {

    void updateMessageIsRead(Long messageId);

    void deleteMessage(Long messageId);

    Message findMessageByMessageId(Long messageId);

    int countByIsRead(Integer isRead);

    Page<Message> findAllMessageBySearch(Pages pages, String name, String email);

    List<Message> findAllByIsRead(Integer isRead);

    Long count();
}
