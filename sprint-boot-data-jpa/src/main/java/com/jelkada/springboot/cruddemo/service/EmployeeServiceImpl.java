package com.jelkada.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jelkada.springboot.cruddemo.dao.EmployeeRepository;
import com.jelkada.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo; 

	@Override
	public List<Employee> findAll() {
		
		return employeeRepo.findAll();
	}

	@Override
	public Employee findById(int theId) {
		
		Optional<Employee> result = employeeRepo.findById(theId);
		
		Employee theEmployee = null;
		
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
	
		employeeRepo.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		
		employeeRepo.deleteById(theId);
	}

}
