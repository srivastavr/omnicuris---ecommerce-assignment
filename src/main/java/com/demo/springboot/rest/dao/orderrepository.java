package com.demo.springboot.rest.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.springboot.rest.model.Order;

public interface orderrepository extends CrudRepository<Order, Long> {
	public Order findTopByuserOrderByIdDesc(String user);

	public Iterable<Order> findByuserOrderByIdDesc(String user);
}