package com.somil.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.somil.service.DataService;

@Component
@ComponentScan("com.somil")
@Configuration
@EnableJpaRepositories(basePackages="com.somil")
@EntityScan("com.somil")
@EnableTransactionManagement
public class ApplicationStarter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStarter.class);
	
	@Autowired
	private DataService dataService;
	
    public static void main(String[] args) {
    	
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationStarter.class);
		ApplicationStarter launchService = ctx.getBean(ApplicationStarter.class);
    	launchService.test();
		((AnnotationConfigApplicationContext)ctx).close();		
    }
    
    private void test() {
    	getProductBetweenPriceRange();
    }
    

    
    private void getProductBetweenPriceRange() {
    	
    	System.out.println(dataService.getProductAbovePrice(45d));
    }
    
    
}
