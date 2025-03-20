package com.startup.demo.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startup.demo.spring.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{
	
	List<Course> findByNameLikeIgnoringCase(String name);

}
