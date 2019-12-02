package com.zzp.spring.boot.admin.repository;

import com.zzp.spring.boot.admin.domain.WebsiteConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteConfigRepository extends JpaRepository<WebsiteConfig, Long> {
}
