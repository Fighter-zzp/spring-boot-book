package com.zzp.spring.boot.admin.service;

import com.zzp.spring.boot.admin.domain.Link;
import com.zzp.spring.boot.admin.domain.Pages;
import org.springframework.data.domain.Page;

public interface ILinkService {

    Page<Link> findAllBySearch(Pages pages, Long linkId, String linkName);

    Link findLinkByLinkId(Long linkId);

    void saveOrUpdateLink(Link link);

    void deleteLink(Long linkId);

    Long count();
}
