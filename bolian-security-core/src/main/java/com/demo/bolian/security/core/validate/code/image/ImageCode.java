/**
 * 
 */
package com.demo.bolian.security.core.validate.code.image;

import com.demo.bolian.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 *
 */
public class ImageCode extends ValidateCode {
	
	private BufferedImage image; 
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
