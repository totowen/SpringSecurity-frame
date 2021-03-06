/**
 * 
 */
package com.demo.bolian.security.core.social.qq.config;

import com.demo.bolian.security.core.properties.QQProperties;
import com.demo.bolian.security.core.properties.SecurityProperties;
import com.demo.bolian.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;


/**
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id") //只有当配置文件中有对应的配置时，该类才会生效
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
	 * #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
