package com.product.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.product.beans.ProductBean;

@Service
public abstract class ProductRestService {

    public String searchProduct(URL url) {
        String html = null;
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("Response code: " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            html = response.toString();
        } catch (IOException pE) {
            pE.printStackTrace();
        }
        return html;
    }

    public abstract List<ProductBean> getProductDetails(String searchTerm);
}
