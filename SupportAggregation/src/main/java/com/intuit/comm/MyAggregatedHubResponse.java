package com.intuit.comm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intuit.data.Case;

import java.util.List;

public class MyAggregatedHubResponse {
    private List<MyAggregatedHubResponseItem> myAggregatedHubResponseItems;

    @JsonProperty("items")
    public List<MyAggregatedHubResponseItem> getMyAggregatedHubResponseItems() {
        return myAggregatedHubResponseItems;
    }

    public void setMyAggregatedHubResponseItems(List<MyAggregatedHubResponseItem> myAggregatedHubResponseItems) {
        this.myAggregatedHubResponseItems = myAggregatedHubResponseItems;
    }
    public static class MyAggregatedHubResponseItem {
        private List<Case> caseList;
        private int casesNum;
        private int createdErrorCode;
        private int provider;
        private List<String> productNameList;

        @JsonProperty("CaseList")
        public List<Case> getCaseList() {
            return caseList;
        }

        public void setCaseList(List<Case> caseList) {
            this.caseList = caseList;
        }

        @JsonProperty("CasesNum")
        public int getCasesNum() {
            return casesNum;
        }

        public void setCasesNum(int casesNum) {
            this.casesNum = casesNum;
        }

        @JsonProperty("CREATED_ERROR_CODE")
        public int getCreatedErrorCode() {
            return createdErrorCode;
        }

        public void setCreatedErrorCode(int createdErrorCode) {
            this.createdErrorCode = createdErrorCode;
        }

        @JsonProperty("Provider")
        public int getProvider() {
            return provider;
        }

        public void setProvider(int provider) {
            this.provider = provider;
        }

        @JsonProperty("PRODUCT_NAME_LIST")
        public List<String> getProductNameList() {
            return productNameList;
        }

        public void setProductNameList(List<String> productNameList) {
            this.productNameList = productNameList;
        }
    }
}