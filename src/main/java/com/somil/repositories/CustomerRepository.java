package com.somil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.somil.entity.Customer;

@Repository
public interface CustomerRepository 
	extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

}
