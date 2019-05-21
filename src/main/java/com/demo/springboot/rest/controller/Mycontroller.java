package com.demo.springboot.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.rest.model.Order;
import com.demo.springboot.rest.model.Product;
import com.demo.springboot.rest.model.order_details;
import com.demo.springboot.rest.service.Myservice;

@RestController
public class Mycontroller {

	@Autowired
	Myservice service;

	@RequestMapping(value = "/products/all", method = RequestMethod.GET)
	public Iterable<Product> getallProducts() {
		return service.getallproducts();
	}

	@RequestMapping(value = "/getallorders", method = RequestMethod.GET)
	public Iterable<Order> getallorderbyuser(@RequestParam String username) {
		return service.getallorderbyuser(username);
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public List<Product> createaproduct(@RequestBody List<Product> ordermap) {
		for (int i = 0; i < ordermap.size(); i++) {
			service.save(ordermap.get(i));
		}
		return ordermap;
	}

	@RequestMapping(value = "/createorder", method = RequestMethod.POST)
	public HashMap<String, Object> createOrder(@RequestBody List<HashMap<String, Integer>> ordermap,
			@RequestParam String username) {

		Iterable<Product> allproduct = service.getallproducts();
		HashMap<Integer, Integer> prodmap = new HashMap<Integer, Integer>();
		for (Product product : allproduct) {
			prodmap.put(product.getId(), product.getQuantity());
		}
		Order ordermod = new Order();
		List<order_details> orderdetailslist = new ArrayList<>();

		List<HashMap<Integer, String>> response = new ArrayList<HashMap<Integer, String>>();
		for (int oridx = 0; oridx < ordermap.size(); oridx++) {
			Integer id = ordermap.get(oridx).get("productid");
			Integer quantity = ordermap.get(oridx).get("quantity");
			Integer avquantity = prodmap.get(id);
			if (prodmap.containsKey(id) && quantity <= avquantity) {
				updateproductwithid(id, quantity);
				order_details test = new order_details();
				test.setProduct_id(ordermap.get(oridx).get("productid"));
				test.setQuantity(ordermap.get(oridx).get("quantity"));
				orderdetailslist.add(test);

			} else {
				HashMap<Integer, String> hashresp = new HashMap<Integer, String>();
				if (!prodmap.containsKey(id))
					hashresp.put(id, "product not found with this ID");
				else
					hashresp.put(id, "Expected Quantity is not available");
				response.add(hashresp);
			}
		}
		ordermod.setOrderProducts(orderdetailslist);
		ordermod.setUser(username);
		service.addinorders(ordermod);
		HashMap<String,Object> res = new HashMap<String,Object>();
		res.put("sucessOrders",service.getmyorders(username));
		if (!response.isEmpty() && response.size() > 0)
			res.put("failOrders",response);
		return res;
	}

	@RequestMapping(value = "/product/update/{id}", method = RequestMethod.PUT)
	public Object Product(@RequestBody Product proup, @PathVariable int id) throws Exception {
		Product upid = service.getaproduct(id);
		if (upid != null) {
			upid.setName(proup.getName());
			upid.setQuantity(upid.getQuantity() + proup.getQuantity());
			return service.getaproduct(id);
		}
		HashMap<String, String> resp = new HashMap<String, String>();
		resp.put("stat", "fail");
		resp.put("msg", "Product not found to update");
		return resp;

	}
	@DeleteMapping("/product/delete/{id}")
	public Object deleteStudent(@PathVariable int id) {
		HashMap<String, String> resp = new HashMap<String, String>();
		Product upid = service.getaproduct(id);
		if(upid!=null) {
			service.deleteById(id);
			return "deleted SucessFully";
		}
		resp.put("stat", "fail");
		resp.put("msg", "Product not found to delete");
		return resp;
		
	}

	private void updateproductwithid(Integer id, Integer quantity) {
		Product upid = service.getaproduct(id);
		upid.setQuantity(upid.getQuantity() - quantity);
		service.save(upid);

	}
	@RequestMapping(value = "/orders/all", method = RequestMethod.GET)
	public Iterable<Order> getallorders() {
		return service.getallorders();
	}
}