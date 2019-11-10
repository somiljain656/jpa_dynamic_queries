package com.somil.service;

import com.somil.entity.Customer;

public interface DataService {

	public Customer getCustomerByMobileNumber(String mobileNumber);
	
	public Integer insertCustomerData(Customer customer);

}
