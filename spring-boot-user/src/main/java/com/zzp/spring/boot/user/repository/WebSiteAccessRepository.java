package com.zzp.spring.boot.user.repository;

import com.zzp.spring.boot.user.model.WebsiteAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface WebSiteAccessRepository extends JpaRepository<WebsiteAccess,Long> {
    WebsiteAccess getByAccessDateIs(Date accessDate);
}
