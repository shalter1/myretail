package com.myretail.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {
    Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    ProductsService productService;

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable(value = "id") Integer id) {
        return productService.getProduct(id);
    }

    @PutMapping("/products/{id}")
    public void putProduct(@PathVariable(value = "id") Integer id, @RequestBody PriceView inPrice) {
        logger.debug("ProductsController.putProduct(" + id + "), new price = " + inPrice);

        Price newPrice = new Price(id, inPrice.getValue(), inPrice.getCurrency_code());

        productService.putPrice(newPrice);
    }
}
