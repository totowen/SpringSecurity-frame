/**
 * 
 */
package com.bolian.security.app;

import javax.servlet.http.HttpServletRequest;

import com.bolian.security.app.social.AppSingUpUtils;
import com.bolian.security.core.properties.SecurityConstants;
import com.bolian.security.core.social.SocialController;
import com.bolian.security.core.social.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;


/**
 *
 */
@RestController
public class AppSecurityController extends SocialController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
	private AppSingUpUtils appSingUpUtils;
	
	/**
	 * 需要注册时跳到这里，返回401和用户信息给前端
	 * @param request
	 * @return
	 */
	@GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		//每次请求上来的请求信息还是放在session中的，在跳转之前从session中拿出请求信息放到redis中，因为下一个请求上来之后session又是重新创建的。
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		appSingUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
		return buildSocialUserInfo(connection);
	}

}
