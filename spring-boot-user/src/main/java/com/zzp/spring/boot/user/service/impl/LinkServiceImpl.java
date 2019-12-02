package com.zzp.spring.boot.user.service.impl;

import com.zzp.spring.boot.user.model.Link;
import com.zzp.spring.boot.user.repository.LinkRepository;
import com.zzp.spring.boot.user.service.ILinkService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class LinkServiceImpl implements ILinkService {
    @Resource
    private LinkRepository linkRepository;

    @Override
    @Cacheable(value = "link")
    public List<Link> findAllByIsEnable() {
        return linkRepository.findAll();
    }
}
