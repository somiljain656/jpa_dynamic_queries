package com.somil.starter;

import org.springframework.beans.factory.annotation.Autowired;

import com.somil.service.DataService;

public class ApplicationStarter {
	
	@Autowired
	private DataService dataService;
	
    public static void main(String[] args) {
    
    }
  
}
