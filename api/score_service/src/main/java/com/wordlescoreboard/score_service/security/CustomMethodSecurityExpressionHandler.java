package com.wordlescoreboard.score_service.security;

import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    @Override
    protected CustomSecurityExpressionRoot createSecurityExpressionRoot(Authentication authentication, org.aopalliance.intercept.MethodInvocation invocation) {
        return new CustomSecurityExpressionRoot(authentication);
    }
}

