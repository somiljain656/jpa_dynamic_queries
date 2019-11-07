package com.somil.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somil.entity.Order;
import com.somil.repositories.OrderRepository;

@Service
public class OrderCrudService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderCrudService.class);

	@Autowired
	private OrderRepository repository;

	public Order insertOrderData(Order orderData) {
		return repository.save(orderData);
	}

	public List<Order> insertOrderDataList(List<Order> orderDataList) {
		return repository.save(orderDataList);
	}
}
