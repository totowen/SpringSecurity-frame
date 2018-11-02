/**
 * 
 */
package com.demo.bolian.security.core.social.qq.connet;

import com.demo.bolian.security.core.social.qq.api.QQ;
import com.demo.bolian.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;


/**
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * 判断QQ的服务是否还存在
	 * @param api
	 * @return
	 */
	@Override
	public boolean test(QQ api) {
		return true;
	}

	/**
	 * 将ServiceProvider中Api的实现获取的用户信息适配到Connection中
	 * @param api
	 * @param values
	 */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId()); //服务商的用户id=openId
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		//do noting
	}

}
