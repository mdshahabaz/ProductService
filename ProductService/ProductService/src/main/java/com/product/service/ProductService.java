package com.product.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.beans.ProductBean;
import com.product.beans.ProductCompareResponse;

@Service
public class ProductService {

    @Autowired
    AmazonProductRestService amazonProductRestService;

    @Autowired
    FlipkartProductRestService flipkartProductRestService;

    public ProductCompareResponse compareProducts(String searchTerm, String sortBy, String sortOrder) {
        ProductCompareResponse response = new ProductCompareResponse();
        Map<String, List<ProductBean>> productBeanMap = new HashMap<>();
        ExecutorService threadpool = Executors.newCachedThreadPool();
        for (Websites website : Websites.values()) {
            threadpool.execute(() -> {
                ProductRestService productService = getProductService(website.toString());
                if (null != productService) {
                    productBeanMap.put(website.toString(), productService.getProductDetails(searchTerm));
                }
            });
        }
        threadpool.shutdown();
        while (!threadpool.isTerminated()) {
            // Threads are waiting to terminate
        }
        productBeanMap.values().forEach(products -> response.getProducts().addAll(products));
        response.setTotalProducts(response.getProducts().size());
        if ("price".equalsIgnoreCase(sortBy)) {
            response.getProducts().sort("high".equalsIgnoreCase(sortOrder) ? Comparator.comparing(ProductBean::getPrice, Comparator.nullsLast(Comparator.reverseOrder())) : Comparator.comparing(ProductBean::getPrice, Comparator.nullsLast(Comparator.naturalOrder())));
        }
        if ("rating".equalsIgnoreCase(sortBy)) {
            response.getProducts().sort("high".equalsIgnoreCase(sortOrder) ? Comparator.comparing(ProductBean::getRating, Comparator.nullsLast(Comparator.reverseOrder())) : Comparator.comparing(ProductBean::getRating, Comparator.nullsLast(Comparator.naturalOrder())));
        }
        return response;
    }

    public ProductRestService getProductService(String website) {
        if (Websites.AMAZON.toString().equalsIgnoreCase(website)) {
            return amazonProductRestService;
        } else if (Websites.FLIPKART.toString().equalsIgnoreCase(website)) {
            return flipkartProductRestService;
        }
        return null;
    }
}

enum Websites {
    AMAZON, FLIPKART
}
