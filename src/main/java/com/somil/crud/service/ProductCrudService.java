package com.somil.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somil.entity.Product;
import com.somil.repositories.ProductRepository;

@Service
public class ProductCrudService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCrudService.class);

	@Autowired
	private ProductRepository repository;

	public Product insertProductData(Product productData) {
		return repository.save(productData);
	}

	public List<Product> insertProductDataList(List<Product> productDataList) {
		return repository.save(productDataList);
	}

}
