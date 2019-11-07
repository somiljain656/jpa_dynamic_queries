package com.somil.exceptions;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class MyAsyncUncaughtExceptionHandler 
	implements AsyncUncaughtExceptionHandler {
    
	private static final Logger LOGGER = LoggerFactory.getLogger("MyAsyncUncaughtExceptionHandler");

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        LOGGER.info("Method Name::" + method.getName());
        LOGGER.info("Exception occurred::" + ex);
    }

} 