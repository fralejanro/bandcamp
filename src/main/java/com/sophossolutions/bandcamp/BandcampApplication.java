package com.sophossolutions.bandcamp;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@SpringBootApplication
public class BandcampApplication {

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory){
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);

		// Load both schema.sql and data.sql into a single CompositeDatabasePopulator
		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
		initializer.setDatabasePopulator(populator);

		return initializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(BandcampApplication.class, args);
	}

}
