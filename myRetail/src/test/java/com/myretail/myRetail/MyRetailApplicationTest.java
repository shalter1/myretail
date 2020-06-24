package com.myretail.myRetail;

import com.myretail.restservice.MyRetailApplication;
import org.apache.logging.log4j.util.EnvironmentPropertySource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.restservice.PriceView;
import com.myretail.restservice.ProductsController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {GlobalConfig.class, MyRetailApplication.class})
public class MyRetailApplicationTest {

	@Value("${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ProductsController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products/13860428",
				String.class)).contains("The Big Lebowski");

		HttpHeaders headers = new HttpHeaders();

		PriceView pv = new PriceView(2375, "USD");

		HttpEntity<PriceView> requestEntity = new HttpEntity<PriceView>(pv, headers);
		HttpEntity<PriceView[]> response = restTemplate.exchange("http://localhost:" + port + "/products/13860428", HttpMethod.PUT, requestEntity, PriceView[].class);

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products/13860428",
				String.class)).contains("The Big Lebowski");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products/13860428",
				String.class)).contains("2375");

	}



}
