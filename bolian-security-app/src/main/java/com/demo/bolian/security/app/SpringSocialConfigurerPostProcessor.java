package com.demo.bolian.security.app;

import com.demo.bolian.security.core.social.ImoocSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 所有的Bean初始化之前和初始化之后都要经过的两个方法
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在所有Bean初始化结束之后将 SpringSocialConfigurer 这个bean中的signUpUrl这个设置改掉
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(StringUtils.equals(beanName,"imoocSocialSecurityConfig")){
            ImoocSpringSocialConfigurer configurer = (ImoocSpringSocialConfigurer)bean;
            configurer.signupUrl("/social/signUp");
            return configurer;
        }
        return bean;
    }
}
