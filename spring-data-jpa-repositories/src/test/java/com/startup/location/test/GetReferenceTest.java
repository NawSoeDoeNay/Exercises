package com.startup.location.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.startup.location.config.JpaConfiguration;
import com.startup.location.model.repo.StateRepo;

import jakarta.transaction.Transactional;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@Sql(scripts = {
		"/init-state.sql",
		"/load-data.sql"
})
public class GetReferenceTest {

	@Autowired
	private StateRepo repo;
	
	@Test
	@Transactional
	void test() {
		var entity = repo.getReferenceById(1);
		System.out.println(entity.getName());
	}
}
