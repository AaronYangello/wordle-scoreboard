package com.wordlescoreboard.score_service.config;


import com.wordlescoreboard.score_service.security.CustomMethodSecurityExpressionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected CustomMethodSecurityExpressionHandler createExpressionHandler() {
        return new CustomMethodSecurityExpressionHandler();
    }
}


