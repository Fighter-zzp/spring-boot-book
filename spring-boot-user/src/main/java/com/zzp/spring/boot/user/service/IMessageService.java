package com.zzp.spring.boot.user.service;

import com.zzp.spring.boot.user.model.Message;

public interface IMessageService {
    // 保存信息
    void saveMessage(Message message);
}
