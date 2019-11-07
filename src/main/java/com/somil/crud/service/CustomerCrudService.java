package com.somil.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somil.entity.Customer;
import com.somil.repositories.CustomerRepository;

@Service
public class CustomerCrudService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCrudService.class);

	@Autowired
	private CustomerRepository repository;

	public Customer insertCustomerData(Customer customerData) {
		return repository.save(customerData);
	}

	public List<Customer> insertCustomerDataList(List<Customer> customerDataList) {
		return repository.save(customerDataList);
	}
}
