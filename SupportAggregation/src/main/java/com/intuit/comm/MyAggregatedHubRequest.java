package com.intuit.comm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyAggregatedHubRequest {
    private long customerId;
    private int provider;
    private int createdErrorCode;
    private String status;
    private String productName;
    // Since whoever who planned the exercise represents the dates in a non standard way such as "3/21/20196:33" - I just skip the date filtering.

    @JsonProperty("CustomerID")
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("Provider")
    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    @JsonProperty("CREATED_ERROR_CODE")
    public int getCreatedErrorCode() {
        return createdErrorCode;
    }

    public void setCreatedErrorCode(int createdErrorCode) {
        this.createdErrorCode = createdErrorCode;
    }

    @JsonProperty("STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
