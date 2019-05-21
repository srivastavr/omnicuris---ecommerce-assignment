package com.demo.springboot.rest.service;

import com.demo.springboot.rest.model.Order;
import com.demo.springboot.rest.model.Product;

public interface Myservice {

	public Iterable<Product> getallproducts();

	public Product save(Product product);

	Product getProduct(long id) throws Exception;

	public Order addinorders(Order ordermod);

	public Order getmyorders(String username);

	public Product getaproduct(Integer id);

	public Iterable<Order> getallorderbyuser(String username);

	void deleteById(int id);

	public Iterable<Order> getallorders();

}