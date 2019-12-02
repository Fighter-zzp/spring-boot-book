package com.zzp.spring.boot.admin.repository;

import com.zzp.spring.boot.admin.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

    Tag findByTagName(String tagName);

    Integer countByTagInputDate(Date tagInputdate);
}
