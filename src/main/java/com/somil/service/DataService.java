package com.somil.service;

import java.util.Calendar;
import java.util.List;

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
	 * Method to find a customer by mobile number
	 * @param mobileNumber
	 * @return customer data
	 */
	public Customer getCustomerByMobileNumber(String mobileNumber);

	/**
	 * Method to get products above a certain price
	 * @param price
	 * @return list of products
	 */
	public List<Product> getProductAbovePrice(Double price);

	/**
	 * Method to get orders between certain dates
	 * @param startDate
	 * @param endDate
	 * @return list of orders
	 */
	public List<Order> getOrderBetweenDates(Calendar startDate, Calendar endDate);

	/**
	 * Method to get orders below a certain price
	 * @param price
	 * @return list of orders
	 */
	public List<Order> getOrdersBelowPrice(Double price);


}
