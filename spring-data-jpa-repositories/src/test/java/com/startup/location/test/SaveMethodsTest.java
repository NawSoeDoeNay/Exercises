package com.startup.location.test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
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
	public void test_insert(String name, Type type, String region, String capital, int population) {

		// prepare input
		var input = new State(name, type, region, capital, population);

		// execute test method
		var result = repo.save(input);

		// check result
		assertThat(result, hasProperty("id", is(1)));
	}
	
	@ParameterizedTest
	@Sql(scripts = {
			"/init-state.sql",
			"/load-data.sql"
	})
	@CsvSource({
		"1,Test name, Region,East, Test Capital, 10000"
	})
	public void test_update(int id, String name, Type type, String region, String capital, int population) {
		
		var input = new State(id, name, type, region, capital, population);
		
		repo.save(input);
		
		var result = repo.findById(id).get();
		
		assertThat(result, allOf(
				notNullValue(),
				hasProperty("id",is(id)),
				hasProperty("name",is(name)),
				hasProperty("type",is(type)),
				hasProperty("region",is(region)),
				hasProperty("capital",is(capital)),
				hasProperty("population",is(population))
				));
		
	}
}
