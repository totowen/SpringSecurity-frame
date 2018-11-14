/**
 * 
 */
package com.bolian.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 */
@SpringBootApplication(scanBasePackages = {"com.bolian.security"})//用于扫描@Controller @Service
@RestController
@EnableSwagger2
@EnableJpaRepositories("com.bolian.security")//用于扫描Dao @Repository
@EntityScan("com.bolian.security")//用于扫描JPA实体类 @Entity
public class DemoApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello spring security";
	}

}
