package com.demo.springboot.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springboot.rest.dao.ProductRepository;
import com.demo.springboot.rest.dao.orderrepository;
import com.demo.springboot.rest.model.Order;
import com.demo.springboot.rest.model.Product;

@Service
public class Myserviceimpl implements Myservice {

	@Autowired
	ProductRepository productrdao;
	@Autowired
	orderrepository orepository;

	@Override
	public Order addinorders(Order ordermod) {
		return orepository.save(ordermod);
	}

	@Override
	public Iterable<Product> getallproducts() {
		return productrdao.findAll();
	}
	@Override
	public Iterable<Order> getallorders() {
		return orepository.findAll();
	}
	

	@Override
	public Iterable<Order> getallorderbyuser(String user) {
		return orepository.findByuserOrderByIdDesc(user);
	}

	@Override
	public Order getmyorders(String username) {
		return orepository.findTopByuserOrderByIdDesc(username);
	}

	@Override
	public Product save(Product product) {
		return productrdao.save(product);
	}

	@Override
	public Product getaproduct(Integer id) {
		return productrdao.findById(id);
	}

	@Override
	public Product getProduct(long id) throws Exception {
		return productrdao.findById(id).orElseThrow(() -> new Exception());
	}
	@Override
	public void deleteById(int id)  {
		productrdao.deleteById( id);
		return;
	}
}