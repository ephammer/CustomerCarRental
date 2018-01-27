package com.ephraimhammer.jct.customercarrental.control.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ephraimhammer.jct.customercarrental.R;

public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;

    //UI elements
    Button mButtonLogin;
    Button mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref= getApplicationContext().getSharedPreferences(
                getString(R.string.preference_login), Context.MODE_PRIVATE);

        // Checks if the user is already signed in
        if(isSignedIn())
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // Initialize UI elements
        setContentView(R.layout.activity_welcome);
        mButtonLogin = findViewById(R.id.button_signin);
        mButtonRegister = findViewById(R.id.button_registration);

        // Set OnClickListeners
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LogInActivity.class));
                finish();
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

    }

    private boolean isSignedIn()
    {
        return sharedPref.getBoolean(getString(R.string.signedIn), false);
//        return false;
    }
}
