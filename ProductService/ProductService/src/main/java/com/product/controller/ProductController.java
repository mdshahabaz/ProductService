package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.beans.ProductCompareResponse;
import com.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String sayHello() {
        return "hello products";
    }

    @GetMapping("/")
    public String sayHello1() {
        return "hello products/";
    }

    @GetMapping("/compare")
    public ProductCompareResponse compareProducts(@RequestParam("searchTerm")String searchTerm, @RequestParam(name = "sortBy", required = false)String sortBy, @RequestParam(name = "sortOrder", required = false)String sortOrder) {
        return productService.compareProducts(searchTerm, sortBy, sortOrder);
    }
}
