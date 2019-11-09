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
	
    public long getCustomerDataCount(Customer filter) {
        return (long) repository.count(spec(filter));
    }

    public Customer retrieveCustomerData(Customer filter) {
        return repository.findOne(spec(filter));
    }

    public List<Customer> retrieveCustomerDataList(Customer filter, Integer limit, Integer offset) {

        if (filter == null) {
            LOGGER.error("Filter can't be null, returning empty list");
            return Collections.emptyList();
        }

        if (limit == null || offset == null) {
            return repository.findAll(spec(filter));
        }
        Page<Customer> dataList = repository.findAll(spec(filter),
            new PageRequest(offset, limit));
        return dataList.getContent();
    }

    private Specification<Customer> spec(Customer filter) {

        return new Specification<Customer>() {

            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (filter != null) {

                    if (filter.getCustomerId() != null) {
                        predicates.add(cb.equal(root.get("customerId"), filter.getCustomerId()));
                    }

                    if (filter.getCustomerName() != null) {
                        predicates.add(cb.equal(root.get("customerName"), filter.getCustomerName()));
                    }

                    if (filter.getMobileNumber() != null) {
                        predicates.add(cb.equal(root.get("mobileNumber"), filter.getMobileNumber()));
                    }

                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
