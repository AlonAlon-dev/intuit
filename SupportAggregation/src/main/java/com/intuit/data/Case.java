package com.intuit.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Case {
    private long caseId;
    private long customerId;
    private int provider;
    private int createdErrorCode;
    private String status;
    private String ticketCreationDate;
    private String lastModifiedDate;
    private String productName;

    @JsonProperty("CaseID")
    public long getCaseId() {
        return caseId;
    }

    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }

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

    @JsonProperty("TICKET_CREATION_DATE")
    public String getTicketCreationDate() {
        return ticketCreationDate;
    }

    public void setTicketCreationDate(String ticketCreationDate) {
        this.ticketCreationDate = ticketCreationDate;
    }

    @JsonProperty("LAST_MODIFIED_DATE")
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @JsonProperty("PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}