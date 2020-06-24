package com.myretail.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class MyRetailApplication {

	@Autowired
	private PriceRepository repository;

	@Value("${set.up}")
	private String setUp;

	@Bean
	public CommandLineRunner setUp() {
		return args -> {
				if (setUp.equals("true")) {
					repository.deleteAll();
					repository.save(new Price(13860428,  13.49, "USD"));
					repository.save(new Price(54456119,  3.25, "USD"));
					repository.save(new Price(13264003,  3.28, "USD"));
					//repository.save(new Price(12954218, 1.38, "USD"));
				}
		};
	}

	public static void main(String[] args) {

		run(MyRetailApplication.class, args);
	}

}
