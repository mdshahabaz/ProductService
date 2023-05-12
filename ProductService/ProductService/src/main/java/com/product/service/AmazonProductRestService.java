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
public class AmazonProductRestService extends ProductRestService {

    public static final String URL = "https://www.amazon.in/";

    public List<ProductBean> getProductDetails(String searchTerm) {
        List<ProductBean> productBeans = new ArrayList<>();
        try {
            StringBuilder searchUrl = new StringBuilder(URL);
            searchUrl.append("s?k=");
            searchUrl.append(searchTerm);
            URL url = new URL(searchUrl.toString());
            String html = searchProduct(url);

            // parse and get the products
            Document doc = Jsoup.parse(html);
            Elements links = doc.select("body");
            for (Element link : links) {
                ProductBean productBean = new ProductBean();
                String href = link.attr("href");
                productBeans.add(productBean);
                productBean.setUrl(URL);
            }
        } catch (MalformedURLException pE) {
            pE.printStackTrace();
        }
        return productBeans;
    }
}
