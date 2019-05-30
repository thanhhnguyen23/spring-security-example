package com.thanhhnguyen23.springsecurity.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.thanhhnguyen23.springsecurity.model.Course;
import com.thanhhnguyen23.springsecurity.model.Student;


@Component
public class StudentService {

	private static List<Student> students = new ArrayList<>();

	static {
		Course course1 = new Course("Course1", "Spring", "10 Steps",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));

		Student thanh = new Student("Student1", "Thanh Nguyen",
				"Hiker, Programmer and Hacker", new ArrayList<>(
						Arrays.asList(course1, course2)));

		students.add(thanh);
	}
	public Student retrieveStudent(String studentId) {
		for(Student student : students) {
			if(student.getId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}
	public List<Course> retrieveCourses(String studentId){
		Student student = retrieveStudent(studentId);
		
		if(student == null) {
			return null;
		}
		return student.getCourses();
	}
}
