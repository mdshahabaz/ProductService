package com.product.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCompareResponse {
    
    private List<ProductBean> products;
    
    @JsonProperty("total_products_considered")
    private int totalProducts;

    public List<ProductBean> getProducts() {
        if (products == null) {
            products = new ArrayList<>();
        }
        return products;
    }

    public void setProducts(List<ProductBean> pProducts) {
        products = pProducts;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int pTotalProducts) {
        totalProducts = pTotalProducts;
    }
}
