package com.somil.service;

import com.somil.entity.Customer;

public interface DataService {

	Customer getCustomerByMobileNumber(String mobileNumber);

}
