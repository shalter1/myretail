package com.myretail.myRetail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Validator;

@TestConfiguration
@SpringBootConfiguration
@PropertySource(value = "classpath:test.properties", encoding = "UTF-8")
public class GlobalConfig {

    @Bean(name = "JsonMapper")
    public JsonMapper jsonMapper() {

        return new JsonMapper();
    }




}