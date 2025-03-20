package com.startup.demo.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startup.demo.spring.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

}
