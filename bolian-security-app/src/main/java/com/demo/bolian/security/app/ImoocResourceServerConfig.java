package com.demo.bolian.security.app;

import com.demo.bolian.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.demo.bolian.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.demo.bolian.security.core.properties.SecurityConstants;
import com.demo.bolian.security.core.properties.SecurityProperties;
import com.demo.bolian.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableResourceServer //资源服务器注解
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailureHandler);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(imoocSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getSignInPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/regist","/social/signUp")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
