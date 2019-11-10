package com.somil.service.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somil.constants.QueryTypeEnum;
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
	
	@Override
	public List<Product> getProductAbovePrice(Double price) {
		Product filter = new Product();
		filter.setPriceQueryType(QueryTypeEnum.GREATER);
		filter.setProductPrice(price);
		return productCrudService.retrieveProductDataList(filter, null, null);
	}
	
	@Override
	public List<Order> getOrdersBelowPrice(Double price) {
		Order filter = new Order();
		filter.setPriceQueryType(QueryTypeEnum.LESS);
		filter.setTotalPrice(price);
		return orderCrudService.retrieveOrderDataList(filter, null, null);
	}
	
	@Override
	public List<Order> getOrderBetweenDates(Calendar startDate, Calendar endDate) {
		Order filter = new Order();
		filter.setStartDate(startDate);
		filter.setEndDate(endDate);
		filter.setDateQueryType(QueryTypeEnum.BETWEEN);
		return orderCrudService.retrieveOrderDataList(filter, null, null);
	}

}
