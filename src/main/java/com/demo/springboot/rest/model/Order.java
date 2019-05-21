package com.demo.springboot.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int id;

	 private Date created;

	@Column(name = "user_name")
	private String user;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private List<order_details> orderProducts = new ArrayList<>();


	public int getId() {
		return id;
	}

	@PrePersist
	  protected void onCreate() {
	    created = new Date();
	  }


	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<order_details> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<order_details> orderProducts) {
		this.orderProducts = orderProducts;
	}

}