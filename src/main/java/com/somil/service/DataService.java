package com.somil.service;

import com.somil.entity.Customer;
import com.somil.entity.Order;
import com.somil.entity.Product;

public interface DataService {
	
	/**
	 * @param customer
	 * @return customer id
	 */
	public Integer insertCustomerData(Customer customer);

	/**
	 * @param product
	 * @return product id
	 */
	public Integer insertProductData(Product product);

	/**
	 * @param order
	 * @return order id
	 */
	public Integer insertOrderData(Order order);

	/**
	 * find a customer by mobile number
	 * @param mobileNumber
	 * @return customer data
	 */
	public Customer getCustomerByMobileNumber(String mobileNumber);


}
