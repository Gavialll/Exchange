package com.gavial.exchange;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gavial.exchange.parser.ThreadConnect;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class Archive extends AppCompatActivity {
    private AdView mAdView;

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.archive);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button back = findViewById(R.id.back);
        Button search = findViewById(R.id.search);
        TextView dateText = findViewById(R.id.editDate);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date localDate = new Date();
        dateText.setHint(dateFormat.format(localDate));

        dateText.setOnEditorActionListener((v, actionId, event) -> {

            addCurrenciesToView();

            InputMethodManager inputManager =
                    (InputMethodManager) this.
                            getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            return true;
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(Archive.this, MainActivity.class);
            startActivity(intent);
        });

        search.setOnClickListener(v -> {
            addCurrenciesToView();
        });
    }

    public void addCurrenciesToView(){

        TextView logo =findViewById(R.id.logoArchive);
        Date localDate = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Button search = findViewById(R.id.search);

        TextView dateText = findViewById(R.id.editDate);
        dateText.setHint(dateFormat.format(localDate));

        if (!dateText.getText().toString().matches("\\d{2}.\\d{2}.\\d{4}")) {
            Toast toast1 = Toast.makeText(this, "Невідомий формат дати",Toast.LENGTH_LONG);
            toast1.show();
            dateText.setText(dateFormat.format(localDate));
        }


        MainActivity.currencies = null;
        search.setWidth(0);
        search.setHeight(0);
        logo.setWidth(0);
        logo.setHeight(0);

        Thread thread = new ThreadConnect(dateText.getText().toString());

        ProgressDialog dialog  = new ProgressDialog(this);
        dialog.setMessage("Загрузка...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.listView1);
        Collections.reverse(MainActivity.currencies.getExchangeRate());
        listView.setAdapter(new CustomListAdapter(this, MainActivity.currencies.getExchangeRate()));

        dialog.hide();
    }
}
