package com.startup.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.startup.demo.spring.config.JpaConfiguration;
import com.startup.demo.spring.entity.Course;
import com.startup.demo.spring.repo.CourseRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class CourseRepoJavaTest {

	@Autowired
	private CourseRepo repo;
	
	@ParameterizedTest
	@CsvSource({
		"1,Java Basic,4,300000"
	})
	void create_Test (int id, String name, int duration, int fees) {
		
		//prepare input value
		var input = new Course(name, duration, fees);
		
		//execute test method
		var result = repo.save(input);
		
		//confirm result
		assertEquals(id, result.getId());
		
	}
	
	@ParameterizedTest
	@Sql(statements = {
			"truncate table Course;",
			"insert into Course (name,duration,fees) values ('Java Basic',4,300000);",
			"insert into Course (name,duration,fees) values ('Spring',6,500000);",
			"insert into Course (name,duration,fees) values ('Java EE',6,500000);",
			"insert into Course (name,duration,fees) values ('Angular',4,300000);",
			"insert into Course (name,duration,fees) values ('Flutter',3,400000);"
			
	})
	@CsvSource({
		"Java,2",
		"Flutter,1",
		"One Stop,0"
	})
	void find_by_name_like_test(String name, int count) {
		var result = repo.findByNameLikeIgnoringCase(name.concat("%"));
		assertEquals(count, result.size());
		
	}
}
