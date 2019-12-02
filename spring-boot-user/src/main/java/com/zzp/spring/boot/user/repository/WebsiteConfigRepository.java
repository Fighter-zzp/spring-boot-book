package com.zzp.spring.boot.user.repository;

import com.zzp.spring.boot.user.model.WebsiteConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteConfigRepository extends JpaRepository<WebsiteConfig,Long> {
}
