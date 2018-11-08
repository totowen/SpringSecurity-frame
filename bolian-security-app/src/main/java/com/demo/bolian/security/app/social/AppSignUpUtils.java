package com.demo.bolian.security.app.social;


import com.demo.bolian.security.app.AppSecretException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

@Component
public class AppSignUpUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    /**
     * 用来定位connectionFactory的
     */
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * 保存用户的第三方信息
     * @param request 头部需要包含设备Id
     * @param connectionData
     */
    public void saveConnectionData(WebRequest request, ConnectionData connectionData) {

        redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);


    }

    /**
     * 绑定
     * @param request
     * @param userId
     */
    public void doPostSignUp(WebRequest request,String userId){
        String key = getKey(request);
        if(!redisTemplate.hasKey(key)){
            throw new AppSecretException("无法找到缓存的第三方用户社交账号信息");
        }
        //从缓存中获取第三方用户社交账号信息
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        //通过ConnectionFactoryLocator+三方登陆的providerId创建ConnectionFactory,然后创建连接信息
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId()).createConnection(connectionData);
        //将三方登陆信息与用户信息绑定在一起插入数据库
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);

    }

    private String getKey(WebRequest request) {

        String deviceId = request.getHeader("deviceId");

        if (StringUtils.isBlank(deviceId)) {
            throw new AppSecretException("设备id参数不能为空");
        }
        return "imooc:security:social.connect."+deviceId;
    }


}
