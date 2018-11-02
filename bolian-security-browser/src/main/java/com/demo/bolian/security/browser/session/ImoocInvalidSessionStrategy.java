/**
 * 
 */
package com.demo.bolian.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;

/**
 * 当检测到会话过期时，进行的跳转逻辑
 */
public class ImoocInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	public ImoocInvalidSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
	}

}
