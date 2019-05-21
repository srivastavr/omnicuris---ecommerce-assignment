package com.demo.springboot.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springboot.rest.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	public Product findById(Integer id);

	@Transactional
	public void deleteById(Integer id);

}