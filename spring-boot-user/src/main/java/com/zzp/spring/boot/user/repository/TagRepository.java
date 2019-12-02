package com.zzp.spring.boot.user.repository;

import com.zzp.spring.boot.user.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByTagName(String tagName);
}
