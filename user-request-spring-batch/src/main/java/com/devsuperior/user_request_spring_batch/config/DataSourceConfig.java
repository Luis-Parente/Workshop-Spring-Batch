package com.devsuperior.user_request_spring_batch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource springBatchDB() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSource appDB() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public PlatformTransactionManager transactionManagerApp(@Qualifier("appDB") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
