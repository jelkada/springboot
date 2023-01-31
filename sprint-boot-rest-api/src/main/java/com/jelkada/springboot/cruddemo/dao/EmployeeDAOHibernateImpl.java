package com.jelkada.springboot.cruddemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jelkada.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		Session currSession = entityManager.unwrap(Session.class);
		
		Query<Employee> getEmployeesQuery = currSession.createQuery("from Employee", Employee.class);
		
		List<Employee> employees = getEmployeesQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		Session currSession = entityManager.unwrap(Session.class);
		
		return currSession.get(Employee.class, theId);
	}

	@Override
	public void save(Employee theEmployee) {
		
		Session currSession = entityManager.unwrap(Session.class);
		
		currSession.merge(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		
		Session currSession = entityManager.unwrap(Session.class);
		
		MutationQuery delEmployeesQuery = currSession.createMutationQuery("delete from Employee where id=:employeeId");
		delEmployeesQuery.setParameter("employeeId", theId);
		
		delEmployeesQuery.executeUpdate();
	}

}
