package com.jelkada.springrest.service;

import java.util.List;

import com.jelkada.springrest.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);	
}
