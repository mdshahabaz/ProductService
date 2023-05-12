package com.product.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductBean {

    private String title;
    private String url;
    @JsonProperty("total_review_count")
    private String reviewCount;
    private String rating;
    private String price;
    private String website;

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String pUrl) {
        url = pUrl;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String pReviewCount) {
        reviewCount = pReviewCount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String pRating) {
        rating = pRating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String pPrice) {
        price = pPrice;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String pWebsite) {
        website = pWebsite;
    }
}
