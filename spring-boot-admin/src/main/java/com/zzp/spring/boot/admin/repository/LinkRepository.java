package com.zzp.spring.boot.admin.repository;


import com.zzp.spring.boot.admin.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkRepository extends JpaRepository<Link, Long>, JpaSpecificationExecutor<Link> {

}
