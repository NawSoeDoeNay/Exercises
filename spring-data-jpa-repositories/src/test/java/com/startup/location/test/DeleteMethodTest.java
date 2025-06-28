package com.startup.location.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.startup.location.config.JpaConfiguration;
import com.startup.location.model.entity.State;
import com.startup.location.model.entity.State.Type;
import com.startup.location.model.repo.StateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@Sql(scripts = {
		"/init-state.sql",
		"/load-data.sql"
})
public class DeleteMethodTest {
	
	@Autowired
	private StateRepo repo;
	
	@ParameterizedTest
	@CsvSource(value = {"1	Ayeyarwady	Region	Lower	Pathein	6184829	14"},
					delimiter = '\t')
	void delete_test_by_entity(int id, String name, Type type, String region, String capital, int population, long remain) {
		var input = new State(id,name, type, region, capital, population);
		
		repo.delete(input);
		
		assertThat(repo.count(), is(remain));
		
	}
	
	@ParameterizedTest
	@CsvSource("1, 14")
	void delete_test_by_id(int id, long remain) {
		
		repo.deleteById(id);
		
		assertThat(repo.count(), is(remain));
		
	}

}
