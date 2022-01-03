package com.gavial.exchange.parser;

import com.gavial.exchange.MainActivity;
import com.gavial.exchange.model.Currencies;
import com.gavial.exchange.model.Currency;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ThreadConnect extends Thread{
    private String date;

    @Override
    public void run(){
        try{
            String str;
            Date localDate;
            if(date == null) {
                // Текущее время
                localDate = new Date();
                // Форматирование времени как "день.месяц.год"
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

                String dateText = dateFormat.format(localDate);
                str = "https://api.privatbank.ua/p24api/exchange_rates?json&date=" + dateText;
            }
            else {
                str = "https://api.privatbank.ua/p24api/exchange_rates?json&date=" + date;
            }

            URL obj = new URL(str);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            InputStream response = con.getInputStream();
            Scanner s = new Scanner(response).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            //Parser Json to Object
            Gson g = new Gson();
            MainActivity.currencies = g.fromJson(result, Currencies.class);

            List<Currency> currencyList = new ArrayList<>();

            for(Currency currency : MainActivity.currencies.getExchangeRate()) {
                if(currency.getPurchaseRate() == null) continue;
                currencyList.add(currency);
            }

            MainActivity.currencies.setExchangeRate(currencyList);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ThreadConnect() {
    }

    public ThreadConnect(String date) {
        //26.12.2021
        this.date = date;
    }

}
