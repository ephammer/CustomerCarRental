package com.ephraimhammer.jct.customercarrental.control.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.Task;
import com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const;

import java.util.Random;

public class AddClientActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText mailEditText;
    private EditText phoneEditText;
    private EditText creditCardEditText;
    private EditText passwordEditText;

    private ContentValues contentValues;
    private Button addButton;
    private int sectr;



    private void getViews(){
        firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
        lastNameEditText  = (EditText)findViewById(R.id.lastNameEditText);
        mailEditText = (EditText)findViewById(R.id.emailEditText);
        phoneEditText = (EditText)findViewById(R.id.phoneEditText);
        creditCardEditText  = (EditText)findViewById(R.id.cCardEditText);
        addButton = (Button)findViewById(R.id.addButton);
        passwordEditText= (EditText)findViewById(R.id.passwordEditText);

    }

    private void setOnClickListeners() {
        if (addButton != null) {
            addButton.setOnClickListener(this);
        }
    }

    //Refresh After adding a Client.
    private void refreshInputsText()
    {
        firstNameEditText.setText(null);
        lastNameEditText.setText(null);
        mailEditText.setText(null);
        phoneEditText.setText(null);
        creditCardEditText.setText(null);
        passwordEditText.setText(null);
    }


    private void addClient() {
        //TODO:Verifier les inputs.

        Random rand = new Random();

        sectr = rand.nextInt(50) + 1;

        contentValues = new ContentValues();



        contentValues.put(Academy_Const.ClientConst.FIRSTNAME,firstNameEditText.getText().toString());
        contentValues.put(Academy_Const.ClientConst.LASTNAME, lastNameEditText.getText().toString());
        contentValues.put(Academy_Const.ClientConst.PHONE_NUMBER, phoneEditText.getText().toString());
        contentValues.put(Academy_Const.ClientConst.MAIL_ADDRESS, mailEditText.getText().toString());
        contentValues.put(Academy_Const.ClientConst.CREDIT_CAR_NUMBER, creditCardEditText.getText().toString());
        contentValues.put(Academy_Const.ClientConst.PASSWORD, passwordEditText.getText().toString());
        contentValues.put(Academy_Const.ClientConst.SECTOR, sectr);

        //TODO:ASYNCTASK

       new Task.AddClientTask(this).execute(contentValues);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        getViews();
        setOnClickListeners();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.addButton:
                addClient();
                refreshInputsText();
                break;



        }
    }

}



