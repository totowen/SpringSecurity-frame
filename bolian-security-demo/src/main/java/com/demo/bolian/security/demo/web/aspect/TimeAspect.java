package com.demo.bolian.security.demo.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 切片能拿到执行方法的参数，但是拿不到方法原始的request和response
 */
@Aspect
@Component
public class TimeAspect {


    //某类中的任何参数的任何方法，任一返回值的方法

    /**
     * @param pjp 拦截住的方法的一些信息对象
     * @return
     */
    @Around("execution(* com.demo.bolian.security.demo.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        for (Object arg :
                args) {
            System.out.println("arg is "+arg);
        }

        long start = new Date().getTime();

        Object object = pjp.proceed();//相当于doFilter执行被拦截的方法。

        System.out.println("time aspect 耗时: "+(new Date().getTime()-start));

        System.out.println("time aspect end");

        return object;
    }

}
