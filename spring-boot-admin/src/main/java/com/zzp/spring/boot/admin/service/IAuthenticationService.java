package com.zzp.spring.boot.admin.service;

import org.springframework.security.core.Authentication;

public interface IAuthenticationService {
    Authentication getAuthentication();
}
