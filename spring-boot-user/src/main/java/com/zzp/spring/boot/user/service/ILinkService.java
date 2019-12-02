package com.zzp.spring.boot.user.service;

import com.zzp.spring.boot.user.model.Link;

import java.util.List;

public interface ILinkService {
    List<Link> findAllByIsEnable();
}
