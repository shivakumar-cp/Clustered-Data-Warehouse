package com.bloomberg;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * This Class is the Spring starter class
 * 
 * @author Shivakumar CP
 * 
 */
@SpringBootApplication
public class SpringBootWebApplication {

	private static final String MAX_FILESIZE = "5120MB";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	/**
	 * This method available for resolving a SessionFactory by JPA persistence
	 * unit name
	 * 
	 */
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(MAX_FILESIZE);
		factory.setMaxRequestSize(MAX_FILESIZE);
		return factory.createMultipartConfig();
	}

}