package com.zzp.spring.boot.admin.repository;

import com.zzp.spring.boot.admin.domain.WebsiteAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface WebsiteAccessRepository extends JpaRepository<WebsiteAccess, Long> {

    WebsiteAccess getByAccessDateIs(Date accessDate);
}
