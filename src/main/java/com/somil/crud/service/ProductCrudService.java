package com.somil.crud.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.somil.constants.QueryTypeEnum;
import com.somil.entity.Product;
import com.somil.repositories.ProductRepository;

@Service
public class ProductCrudService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCrudService.class);

	@Autowired
	private ProductRepository repository;


	/**
	 * Method to save single product data 
	 * @param productData
	 * @return
	 */
	public Product insertProductData(Product productData) {
		return repository.save(productData);
	}

	/**
	 * Method to save multiple product data
	 * @param productDataList
	 * @return
	 */
	public List<Product> insertProductDataList(List<Product> productDataList) {
		return repository.save(productDataList);
	}

	/**
	 * Method to get count of records matching filter
	 * @param filter
	 * @return count of records matching filter in DB 
	 */
	public long getProductDataCount(Product filter) {
		return (long) repository.count(spec(filter));
	}

	/**
	 * Method to get product or null matching filter
	 * @param filter
	 * @return product matching filter or null in DB 
	 */
	public Product retrieveProductData(Product filter) {
		return repository.findOne(spec(filter));
	}

	/**
	 * Method to get list of products matching filter
	 * @param filter
	 * @param limit
	 * @param offset
	 * @return list of products records matching filter in DB
	 */
	public List<Product> retrieveProductDataList(Product filter, Integer limit, Integer offset) {

		if (filter == null) {
			LOGGER.error("Filter can't be null, returning empty list");
			return Collections.emptyList();
		}

		if (limit == null || offset == null) {
			return repository.findAll(spec(filter));
		}
		Page<Product> dataList = repository.findAll(spec(filter), new PageRequest(offset, limit));
		return dataList.getContent();
	}

	/**
	 * prepares where clause for sql query
	 * @param filter
	 * @return specification from order filter
	 */
	private Specification<Product> spec(Product filter) {

		return new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				if (filter != null) {

					if (filter.getProductId() != null) {
						predicates.add(cb.equal(root.get("productId"), filter.getProductId()));
					}

					if (filter.getProductName() != null) {
						predicates.add(cb.equal(root.get("productName"), filter.getProductName()));
					}

					if (filter.getPriceQueryType() != null && filter.getProductPrice() != null) {
						if (filter.getPriceQueryType().equals(QueryTypeEnum.EQUAL)) {
							predicates.add(cb.equal(root.get("productPrice"), filter.getProductPrice()));
						} else if (filter.getPriceQueryType().equals(QueryTypeEnum.GREATER)) {
							predicates.add(cb.greaterThanOrEqualTo(root.get("productPrice"), filter.getProductPrice()));
						} else if (filter.getPriceQueryType().equals(QueryTypeEnum.LESS)) {
							predicates.add(cb.lessThanOrEqualTo(root.get("productPrice"), filter.getProductPrice()));
						}
					}

				}
				return cb.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
