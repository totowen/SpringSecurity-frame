package com.demo.bolian.security.core.authroize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class ImoocAuthorizeConfigManager implements AuthorizeConfigManager{

    @Autowired
    private Set<AuthorizeConfigProvider> authorizeConfigProviders;


    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider:
        authorizeConfigProviders) {
            authorizeConfigProvider.config(config);
        }
        config.anyRequest().authenticated();

    }
}
