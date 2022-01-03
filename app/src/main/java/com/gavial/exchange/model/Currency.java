package com.gavial.exchange.model;

public class Currency {
    private String baseCurrency;
    private String currency;
    private String saleRateNB;
    private String purchaseRateNB;
    private String saleRate;
    private String purchaseRate;


    public Currency(String baseCurrency, String currency, String saleRateNB, String purchaseRateNB, String saleRate, String purchaseRate) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    public Currency(String baseCurrency, String currency, String saleRateNB, String purchaseRateNB) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(String saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public String getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(String purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public String getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(String saleRate) {
        this.saleRate = saleRate;
    }

    public String getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(String purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return "Currency{" + "baseCurrency='" + baseCurrency + '\'' + ", currency='" + currency + '\'' + ", saleRateNB='" + saleRateNB + '\'' + ", purchaseRateNB='" + purchaseRateNB + '\'' + ", saleRate='" + saleRate + '\'' + ", purchaseRate='" + purchaseRate + '\'' + '}';
    }
}
