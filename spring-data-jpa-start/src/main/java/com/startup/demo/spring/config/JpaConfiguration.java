package com.startup.demo.spring.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@ComponentScan("com.startup.demo.spring")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.startup.demo.spring.repo")
public class JpaConfiguration {

	@Bean
	DataSource datasource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL).build();
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setPackagesToScan("com.startup.demo.spring.entity");
		bean.setJpaPropertyMap(jpaProperties());
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return bean;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	private Map<String, Object> jpaProperties() {
		var map = new HashMap<String, Object>();
		map.put("hibernate.hbm2ddl.auto", "create");
		map.put("hibernate.show_sql", true);
		map.put("hibernate.format_sql", true);
		return map;
	}
}
