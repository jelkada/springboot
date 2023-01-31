package com.jelkada.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jelkada.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
}
