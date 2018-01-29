package com.ephraimhammer.jct.customercarrental.control.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ephraimhammer.jct.customercarrental.R;

public class MapActivity extends AppCompatActivity {

    int numberStreet;
    String street;
    String city;
    Bundle intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        intent = getIntent().getExtras();

        numberStreet = (int)intent.get("numberStreet");
        street = (String)intent.get("street");
        city = (String)intent.get("city");

        WebView webMap = (WebView)findViewById(R.id.web_map_view);

        WebSettings webSettings = webMap.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url = "https://www.google.com/maps/search/?api=1&query="+numberStreet+"+"+street+"+"+city;
        webMap.loadUrl(url);
    }
}
