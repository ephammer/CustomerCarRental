package com.ephraimhammer.jct.customercarrental.control.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;

import com.ephraimhammer.jct.customercarrental.R;

public class AddCommandActivity extends AppCompatActivity {

    long branchId;
    long clientId;
    long carId;
    String startCommandDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_command);
        startCommandDate =  DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date()).toString();
    }
}
