/**
 * 
 */
package com.demo.bolian.security.core.social.qq.connet;

import com.demo.bolian.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;


/**
 * QQ三方登陆连接工厂
 * 	作用：1.创建服务提供商
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 * @param providerId 提供商的唯一标识
	 * @param appId
	 * @param appSecret
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
