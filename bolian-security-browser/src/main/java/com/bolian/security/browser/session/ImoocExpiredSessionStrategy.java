/**
 * 
 */
package com.bolian.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;

import com.bolian.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;


/**
 * 并发登录导致session失效时，默认的处理策略
 * 
 *
 */
public class ImoocExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	public ImoocExpiredSessionStrategy(SecurityProperties securityPropertie) {
		super(securityPropertie);
	}


	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}
	

	@Override
	protected boolean isConcurrency() {
		return true;
	}

}
