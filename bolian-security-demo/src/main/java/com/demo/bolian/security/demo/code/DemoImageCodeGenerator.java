/**
 * 
 */
package com.demo.bolian.security.demo.code;

import com.demo.bolian.security.core.validate.code.ValidateCodeGenerator;
import com.demo.bolian.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;


/**
 *
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	/* (non-Javadoc)
	 * @see com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ImageCode generate(ServletWebRequest request) {
		System.out.println("更高级的图形验证码生成代码");
		return null;
	}

}
