package com.demo.bolian.security.demo.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD}) //注解添加在方法和类属性上
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class) //该注解执行的逻辑
public @interface MyConstraint {

    String message() ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
