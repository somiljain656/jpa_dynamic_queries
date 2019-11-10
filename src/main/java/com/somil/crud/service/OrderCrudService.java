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
import com.somil.entity.Order;
import com.somil.repositories.OrderRepository;

@Service
public class OrderCrudService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderCrudService.class);

	@Autowired
	private OrderRepository repository;
	
	/**
	 * Method to save single order data 
	 * @param orderData
	 * @return
	 */
	public Order insertOrderData(Order orderData) {
		return repository.save(orderData);
	}

	/**
	 * Method to save multiple orders data
	 * @param orderDataList
	 * @return
	 */
	public List<Order> insertOrderDataList(List<Order> orderDataList) {
		return repository.save(orderDataList);
	}
	
    /**
     * Method to get count of records matching filter
     * @param filter
     * @return count of records matching filter in DB 
     */
    public long getOrderDataCount(Order filter) {
        return (long) repository.count(spec(filter));
    }

    /**
     * Method to get order or null matching filter
     * @param filter
     * @return order matching filter or null in DB 
     */
    public Order retrieveOrderData(Order filter) {
        return repository.findOne(spec(filter));
    }

    /**
     * Method to get list of orders matching filter
     * @param filter
     * @param limit
     * @param offset
     * @return list of orders records matching filter in DB
     */
    public List<Order> retrieveOrderDataList(Order filter, Integer limit, Integer offset) {

        if (filter == null) {
            LOGGER.error("Filter can't be null, returning empty list");
            return Collections.emptyList();
        }

        if (limit == null || offset == null) {
            return repository.findAll(spec(filter));
        }
        Page<Order> dataList = repository.findAll(spec(filter), new PageRequest(offset, limit));
        return dataList.getContent();
    }

    /**
     * prepares where clause for sql query
     * @param filter
     * @return specification from order filter
     */
    private Specification<Order> spec(Order filter) {

        return new Specification<Order>() {

            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (filter != null) {
                
                   if (filter.getOrderId() != null) {
                        predicates.add(cb.equal(root.get("orderId"), filter.getOrderId()));
                   }
                   
                   if (filter.getCustomerId() != null) {
                	   predicates.add(cb.equal(root.get("customer").get("customerId"), filter.getCustomerId()));
                   }
                   
                   if (filter.getProductId() != null) {
                	   predicates.add(cb.equal(root.get("product").get("productId"), filter.getProductId()));
                   }
                   
                   if (filter.getQuantity() != null) {
                       predicates.add(cb.equal(root.get("quantity"), filter.getQuantity()));
                   }
                   
                   if (filter.getPriceQueryType() != null && filter.getTotalPrice() != null) {
                	   if (filter.getPriceQueryType().equals(QueryTypeEnum.EQUAL)) {
                           predicates.add(cb.equal(root.get("totalPrice"), filter.getTotalPrice()));
                	   } else if (filter.getPriceQueryType().equals(QueryTypeEnum.GREATER)) {
                           predicates.add(cb.greaterThanOrEqualTo(root.get("totalPrice"), filter.getTotalPrice()));
                	   } else if (filter.getPriceQueryType().equals(QueryTypeEnum.LESS)) {
                           predicates.add(cb.lessThanOrEqualTo(root.get("totalPrice"), filter.getTotalPrice()));
                	   }
                   }
                   
                   if (filter.getDateQueryType() != null) {
                       if (filter.getDateQueryType().equals(QueryTypeEnum.EQUAL) && filter.getCreatedOn() != null) {
                           predicates.add(cb.equal(root.get("createdOn"), filter.getCreatedOn()));
                       } else if (filter.getDateQueryType().equals(QueryTypeEnum.BETWEEN) && filter.getStartDate() != null && filter.getEndDate() != null) {
                           predicates.add(cb.between(root.get("createdOn"), filter.getStartDate(), filter.getEndDate()));
                       }
                   }
                   
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
