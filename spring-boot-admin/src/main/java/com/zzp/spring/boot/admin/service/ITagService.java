package com.zzp.spring.boot.admin.service;

import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.domain.Tag;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface ITagService {

    Page<Tag> findAllBySearch(Pages pages, Long tagId, String tagName);

    Tag saveTag(Tag tag);

    void deleteTagByTagId(Long tagId);

    Tag findTagByTagName(String tagName);

    Integer countByTagInputDate(Date tagInputDate);

    Long count();


}
