package com.zzp.spring.boot.user.service.impl;

import com.zzp.spring.boot.user.model.Tag;
import com.zzp.spring.boot.user.repository.TagRepository;
import com.zzp.spring.boot.user.service.ITagService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
@Service
public class TagServiceImpl implements ITagService {
    @Resource
    private TagRepository tagRepository;

    @Override
    @Cacheable(value = "tag")
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findByTagName(String tagName) {
        return tagRepository.findByTagName(tagName);
    }
}