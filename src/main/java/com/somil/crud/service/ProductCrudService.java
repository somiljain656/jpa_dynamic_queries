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
	
    public long getProductDataCount(Product filter) {
        return (long) repository.count(spec(filter));
    }

    public Product retrieveProductData(Product filter) {
        return repository.findOne(spec(filter));
    }

    public List<Product> retrieveProductDataList(Product filter, Integer limit, Integer offset) {

        if (filter == null) {
            LOGGER.error("Filter can't be null, returning empty list");
            return Collections.emptyList();
        }

        if (limit == null || offset == null) {
            return repository.findAll(spec(filter));
        }
        Page<Product> dataList = repository.findAll(spec(filter),
            new PageRequest(offset, limit));
        return dataList.getContent();
    }

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

                    if (filter.getProductPrice() != null) {
                        predicates.add(cb.equal(root.get("productPrice"), filter.getProductPrice()));
                    }

                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
