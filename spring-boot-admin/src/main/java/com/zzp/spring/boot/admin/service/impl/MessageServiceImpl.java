package com.zzp.spring.boot.admin.service.impl;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Message;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.repository.MessageRepository;
import com.zzp.spring.boot.admin.service.IMessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements IMessageService {
    @Resource
    private MessageRepository messageRepository;

    @Override
    public void updateMessageIsRead(Long messageId) {
        var om = messageRepository.findById(messageId);
        if (om.isPresent()){
            var message = om.get();
            message.setMessageId(messageId);
            messageRepository.save(message);
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public Message findMessageByMessageId(Long messageId) {
        return messageRepository.findById(messageId).get();
    }

    @Override
    public int countByIsRead(Integer isRead) {
        return messageRepository.countByIsRead(isRead);
    }

    @Override
    public Page<Message> findAllMessageBySearch(Pages pages, String name, String email) {
        var pageAble = PageRequest.of(pages.getPage(), pages.getPageSize(), Sort.Direction.DESC, "isRead", "messageInputDate");
        return messageRepository.findAll(this.getWhereClause(name, email), pageAble);
    }

    @Override
    public List<Message> findAllByIsRead(Integer isRead) {
        return messageRepository.findAllByIsRead(Constants.NO);
    }

    @Override
    public Long count() {
        return messageRepository.count();
    }

    private Specification<Message> getWhereClause(String name,String email){
        return (root, query, cb) ->{
            List<Predicate> predicate = new ArrayList<>();
            if (StringUtils.isNotBlank(name)) {
                predicate.add(
                        cb.or(cb.like(root.get("name"), name + "%"))
                );
            }
            if (StringUtils.isNotBlank(email)) {
                predicate.add(
                        cb.or(cb.like(root.get("email"), email + "%"))
                );
            }
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        };
    }
}
