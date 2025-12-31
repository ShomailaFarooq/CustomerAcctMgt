package com.hsbc.CustomerAcctMgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CustomerAcctMgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAcctMgtApplication.class, args);
	}

}
