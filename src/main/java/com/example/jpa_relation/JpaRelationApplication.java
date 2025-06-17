package com.example.jpa_relation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JpaRelationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaRelationApplication.class, args);
	}

}
