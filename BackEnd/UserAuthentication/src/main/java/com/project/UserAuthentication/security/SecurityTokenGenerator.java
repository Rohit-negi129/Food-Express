package com.project.UserAuthentication.security;

import com.project.UserAuthentication.domain.User;

public interface SecurityTokenGenerator {
    String createToken(User user);
}
