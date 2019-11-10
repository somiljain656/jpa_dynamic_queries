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

import com.somil.entity.Customer;
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
    	
    	Customer customer = new Customer();
    	customer.setCustomerName("David");
    	customer.setMobileNumber("2345167891");
    	customer.setAddress("23 Street, Sector 50, Malad");
    	customer.setCity("Mumbai");
    	customer.setState("Maharastra");
    	customer.setCountry("India");
    	
    	System.out.println(customer);
    	
    	System.out.println(dataService.insertCustomerData(customer));
    	System.out.println("SJXX ::"+dataService.getCustomerByMobileNumber("2345167891"));
    	
    }
  
}
