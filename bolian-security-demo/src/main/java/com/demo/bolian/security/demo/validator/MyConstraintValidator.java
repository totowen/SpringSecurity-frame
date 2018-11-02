package com.demo.bolian.security.demo.validator;

import com.demo.bolian.security.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * 1.第一个泛型是需要进行逻辑验证的注解，第二个是被验证属性的类型
 * 2.spring 看到 这个实现会自动注册为bean，不需要添加@Component注解
 * 3.可以在校验器中注入任何bean来辅助校验
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {


    @Autowired
    public HelloService helloService;


    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        //初始化
        System.out.println("my validator init");
    }

    /**
     *
     * @param value 验证的值
     * @param context 上下文，注解的值
     * @return
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("tom");
        System.out.println(value);
        return false;
    }
}
