package com.product.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.product.beans.ProductBean;

@Service
public class FlipkartProductRestService extends ProductRestService {

    public static final String URL = "https://www.flipkart.com/";

    public List<ProductBean> getProductDetails(String searchTerm) {
        List<ProductBean> productBeans = new ArrayList<>();
        try {
            StringBuilder searchUrl = new StringBuilder(URL);
            searchUrl.append("search?q=");
            searchUrl.append(searchTerm);
            URL url = new URL(searchUrl.toString());
            String html = searchProduct(url);
            
            // parse and get the products
            Document doc = Jsoup.parse(html);
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                ProductBean productBean = new ProductBean();
                String href = link.attr("href");
                System.out.println(href);
                productBean.setUrl(URL);
                productBeans.add(productBean);
            }
        } catch (MalformedURLException pE) {
            pE.printStackTrace();
        }
        return productBeans;
    }
}
