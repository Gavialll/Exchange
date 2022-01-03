package com.gavial.exchange.model;

import java.util.List;

public class Currencies {
    private String date;
    private String bank;
    private String baseCurrency;
    private String baseCurrencyLit;
    private List<Currency> exchangeRate;

    public Currencies(String date, String bank, String baseCurrency, String baseCurrencyLit, List<Currency> exchangeRate) {
        this.date = date;
        this.bank = bank;
        this.baseCurrency = baseCurrency;
        this.baseCurrencyLit = baseCurrencyLit;
        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public List<Currency> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(List<Currency> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "Currencies{" + "date='" + date + '\'' + ", bank='" + bank + '\'' + ", baseCurrency='" + baseCurrency + '\'' + ", baseCurrencyLit='" + baseCurrencyLit + '\'' + ", exchangeRate=" + exchangeRate + '}';
    }
}
