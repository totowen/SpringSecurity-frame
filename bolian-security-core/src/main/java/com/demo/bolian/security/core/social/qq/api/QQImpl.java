/**
 * 
 */
package com.demo.bolian.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ServiceProvider-Api(AbstractOAuth2ApiBinding) 获取服务商提供的信息，
 * 每个服务商的信息获取接口都是不一样的，基本都是在这个接口上做统一封装
 *
 *
 * 多实例的对象，每个用户的accessToken等信息是不一样的
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	private String appId;
	
	private String openId;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public QQImpl(String accessToken, String appId) {
		//ACCESS_TOKEN_PARAMETER 在restTemplate发请求的时候会自动把accessToken挂到请求链接后面
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		
		this.appId = appId;
		
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);

		logger.info(result);
		
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}
	
	/* (non-Javadoc)
	 * @see com.imooc.security.core.social.qq.api.QQ#getUserInfo()
	 */
	@Override
	public QQUserInfo getUserInfo(){
		
		String url = String.format(URL_GET_USERINFO, appId, openId);
		String result = getRestTemplate().getForObject(url, String.class);

		logger.info(result);
		
		QQUserInfo userInfo = null;
		try {
			userInfo = objectMapper.readValue(result, QQUserInfo.class);
			userInfo.setOpenId(openId);
			return userInfo;
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败", e);
		}
	}

}
