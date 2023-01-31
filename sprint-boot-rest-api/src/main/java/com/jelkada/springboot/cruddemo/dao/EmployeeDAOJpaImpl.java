package com.jelkada.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jelkada.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		Query getEmployeesQuery = entityManager.createQuery("from Employee", Employee.class);
		
		List<Employee> employees = getEmployeesQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		theEmployee.setId(dbEmployee.getId());	
	}

	@Override
	public void deleteById(int theId) {

		Query deleteQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		deleteQuery.setParameter("employeeId", theId);
		
		deleteQuery.executeUpdate();
	}

}
