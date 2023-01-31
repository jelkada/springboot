package com.jelkada.springrest.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jelkada.springrest.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	@PostConstruct
	private void loadStudents() {
		
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Jimmy", "Elkada"));
		theStudents.add(new Student("David", "Jackson"));
		theStudents.add(new Student("John", "Clark"));	
	}
		
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return theStudents;
	}

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		if (studentId >= theStudents.size() || studentId < 0) {
			throw new StudentNotFoundException("Student Id not found: " + studentId);
		}
		
		return theStudents.get(studentId);
	}
}
