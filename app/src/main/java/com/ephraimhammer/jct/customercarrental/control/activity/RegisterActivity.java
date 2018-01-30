package com.ephraimhammer.jct.customercarrental.control.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.Task;
import com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText mailEditText;
    private EditText phoneEditText;
    private EditText creditCardEditText;
    private EditText passwordEditText;

    private ContentValues contentValues;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // Initialize UI elements
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        mailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        creditCardEditText = findViewById(R.id.cCardEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;

                if(firstNameEditText.getText().toString().trim().length()==0) {
                    firstNameEditText.setHint("Please enter First Name");
                    firstNameEditText.setHintTextColor(Color.RED);
                    flag = false;
                }
                else if(lastNameEditText.getText().toString().trim().length()==0) {
                    lastNameEditText.setHint("Please enter Last Name");
                    lastNameEditText.setHintTextColor(Color.RED);
                    flag = false;
                }
                else if(mailEditText.getText().toString().trim().length()==0) {
                    mailEditText.setHint("Please enter mail");
                    mailEditText.setHintTextColor(Color.RED);
                    flag = false;
                }
                else if(phoneEditText.getText().toString().trim().length()==0) {
                    phoneEditText.setHint("Please enter phone");
                    phoneEditText.setHintTextColor(Color.RED);
                    flag = false;
                }
                else if(creditCardEditText.getText().toString().trim().length()==0) {
                    creditCardEditText.setHint("Please enter Credit Card");
                    creditCardEditText.setHintTextColor(Color.RED);
                    flag = false;
                }
                else if(passwordEditText.getText().toString().trim().length()==0) {
                    passwordEditText.setHint("Please enter password");
                    passwordEditText.setHintTextColor(Color.RED);
                    flag = false;
                }

                if(flag){
                    contentValues = new ContentValues();

                    contentValues.put(Academy_Const.ClientConst.FIRSTNAME,firstNameEditText.getText().toString());
                    contentValues.put(Academy_Const.ClientConst.LASTNAME, lastNameEditText.getText().toString());
                    contentValues.put(Academy_Const.ClientConst.PHONE_NUMBER, phoneEditText.getText().toString());
                    contentValues.put(Academy_Const.ClientConst.MAIL_ADDRESS, mailEditText.getText().toString());
                    contentValues.put(Academy_Const.ClientConst.CREDIT_CAR_NUMBER, creditCardEditText.getText().toString());
                    contentValues.put(Academy_Const.ClientConst.PASSWORD, passwordEditText.getText().toString());

                    new Task.AddClientTask(getApplicationContext()).execute(contentValues);
                    startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                    finish();
                }
            }
        });
    }
}
