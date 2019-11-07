package com.somil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.somil.entity.Product;

@Repository
public interface ProductRepository
	extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
