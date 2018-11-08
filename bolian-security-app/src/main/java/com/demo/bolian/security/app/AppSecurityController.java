package com.demo.bolian.security.app;

import com.demo.bolian.security.app.social.AppSignUpUtils;
import com.demo.bolian.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppSecurityController {

    /**
     * 工具描述：获取当前请求的session对象
     * 当前作用：app的每次请求都会创建一个新的session,所有每次跳转前将用户数据拿出来拼接好返回出去
     */
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;


    @GetMapping("/social/signUp")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        //跳转前将数据拿出来放到redis中去
        appSignUpUtils.saveConnectionData(new ServletWebRequest(request),connection.createData());

        return userInfo;
    }


}
