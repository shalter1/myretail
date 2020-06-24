package com.myretail.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.restservice.exception.ProductDataCorruptException;
import com.myretail.restservice.exception.ProductNotFoundException;
import com.myretail.restservice.exception.ProductTitleMissingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProductsService {
    Logger logger = LoggerFactory.getLogger(ProductsService.class);

    @Autowired
    private PriceRepository priceRepository;

    @Value("${product.url}")
    private String productURL;


    public Product getProduct(Integer id) {
        ProductName product = getProductName(id);
        Price price = getProductPrice(id);

        return new Product(id, product.getTitle(), new PriceView(price.getValue(), price.getCurrency_code()));
    }

    private Price getProductPrice(Integer id) {
        Optional<Price> price = priceRepository.findById(id);

        if (price == null || !price.isPresent()) {
            return new Price(id, -1.0, "");
        }

        return price.get();
    }

    private ProductName getProductName(Integer id) {
        String url = String.format(productURL, id);

        RestTemplate restTemplate = new RestTemplate();
        ProductName result = null;

        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(url, String.class);
        } catch(Exception e) {
            throw new ProductNotFoundException(id.toString());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new ProductDataCorruptException(id.toString());
        }
        JsonNode name = root.path("product");

        JsonNode titleNode = root.at("/product/item/product_description/title");

        if (titleNode.isMissingNode()) {
            throw new ProductTitleMissingException(id.toString());
        }

        result = new ProductName();
        result.setTitle(titleNode.textValue());

        return result;
    }

    public void putPrice(Price price) {
        priceRepository.save(price);
    }
}