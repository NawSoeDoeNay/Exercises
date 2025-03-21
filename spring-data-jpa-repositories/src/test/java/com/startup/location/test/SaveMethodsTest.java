package com.startup.location.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.hamcrest.beans.HasProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.startup.location.config.JpaConfiguration;
import com.startup.location.model.entity.State;
import com.startup.location.model.entity.State.Type;
import com.startup.location.model.repo.StateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class SaveMethodsTest {

	@Autowired
	private StateRepo repo;

	@ParameterizedTest
	@Sql(scripts = "/init-state.sql")
	@CsvFileSource(resources = "/save/test_create.txt", delimiter = '\t')
	public void test_insert(String name, Type type, String region, String capital, int porpulation) {

		// prepare input
		var input = new State(name, type, region, capital, porpulation);

		// execute test method
		var result = repo.save(input);

		// check result
		assertThat(result, hasProperty("id", is(1)));
	}
}
