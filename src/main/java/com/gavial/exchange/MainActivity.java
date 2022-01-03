package com.gavial.exchange;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.gavial.exchange.model.Currencies;
import com.gavial.exchange.parser.ThreadConnect;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    public static Currencies currencies;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {
        });
        AdRequest adRequests = new AdRequest.Builder().build();
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button button = findViewById(R.id.archive_btn);
        Thread thread = new ThreadConnect();

        thread.start();

        while (MainActivity.currencies == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, currencies.getExchangeRate()));

        button.setOnClickListener(v -> {
            InterstitialAd.load(this,"ca-app-pub-6992423362534771/6958006072", adRequests,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAd = interstitialAd;
                            mInterstitialAd.show(MainActivity.this);
                        }
                    });
            Intent intent = new Intent(MainActivity.this, Archive.class);
            startActivity(intent);
        });
    }
}