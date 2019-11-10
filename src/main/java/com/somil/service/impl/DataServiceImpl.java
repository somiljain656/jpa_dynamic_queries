package com.somil.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somil.crud.service.CustomerCrudService;
import com.somil.crud.service.OrderCrudService;
import com.somil.crud.service.ProductCrudService;
import com.somil.entity.Customer;
import com.somil.entity.Order;
import com.somil.entity.Product;
import com.somil.service.DataService;

@Service
public class DataServiceImpl 
	implements DataService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCrudService.class);
	
	@Autowired
	private CustomerCrudService customerCrudService;
	
	@Autowired
	private ProductCrudService productCrudService;
	
	@Autowired
	private OrderCrudService orderCrudService;
	
	@Override
	public Integer insertCustomerData(Customer customer) {
		return customerCrudService.insertCustomerData(customer).getCustomerId();
	}
	
	@Override
	public Integer insertProductData(Product product) {
		return productCrudService.insertProductData(product).getProductId();
	}
	
	@Override
	public Integer insertOrderData(Order order) {
		return orderCrudService.insertOrderData(order).getOrderId();
	}
	
	@Override
	public Customer getCustomerByMobileNumber(String mobileNumber) {
		Customer filter = new Customer();
		filter.setMobileNumber(mobileNumber);
		return customerCrudService.retrieveCustomerData(filter);
	}

}
