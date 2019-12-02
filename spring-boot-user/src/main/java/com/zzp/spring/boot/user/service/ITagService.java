package com.zzp.spring.boot.user.service;

import com.zzp.spring.boot.user.model.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> findAll();

    Tag findByTagName(String tagName);
}
