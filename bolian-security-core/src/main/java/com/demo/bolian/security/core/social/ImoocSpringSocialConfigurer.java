/**
 * 
 */
package com.demo.bolian.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 在SocialAuthenticationFilter 执行对应post请求url的拦截中替换默认url
 */
public class ImoocSpringSocialConfigurer extends SpringSocialConfigurer {
	
	private String filterProcessesUrl;
	
	public ImoocSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}

}
