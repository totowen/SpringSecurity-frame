package com.demo.bolian.security.demo.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class MockQueue {

    /**
     * 下单的消息
     */
    private String placeOrder;

    /**
     * 订单完成的消息
     */
    private String completeOrder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            logger.info("接到下单请求 " + placeOrder);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            logger.info("下单请求处理完毕 " + placeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
