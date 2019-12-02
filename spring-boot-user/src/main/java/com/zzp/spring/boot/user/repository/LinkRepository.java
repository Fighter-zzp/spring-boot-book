package com.zzp.spring.boot.user.repository;

import com.zzp.spring.boot.user.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Long> {
}
