package com.startup.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.startup.demo.spring.entity.Course;
import com.startup.demo.spring.repo.CourseRepo;

@SpringJUnitConfig(locations = "classpath:/jpa-configuration.xml")
public class CourseRepoXmlTest {

	@Autowired
	private CourseRepo repo;
	
	@ParameterizedTest
	@CsvSource({
		"1,Java Basic,4,300000"
	})
	void createTest (int id, String name, int duration, int fees) {
		
		//prepare input value
		var input = new Course(name, duration, fees);
		
		//execute test method
		var result = repo.save(input);
		
		//confirm result
		assertEquals(id, result.getId());
		
	}
}
