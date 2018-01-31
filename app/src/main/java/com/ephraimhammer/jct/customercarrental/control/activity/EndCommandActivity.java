package com.ephraimhammer.jct.customercarrental.control.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.Task;
import com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Command;
import com.ephraimhammer.jct.customercarrental.model.entities.FUEL_STATE;

import java.util.List;

public class EndCommandActivity extends AppCompatActivity {

    Command command;
    private Long commandID;
    private Long clientID;
    private SharedPreferences sharedPref;
    MySql_DBManager Manager = MySql_DBManager.getInstance();

    //UI elements
    TextView commandIDTextView;
    TextView carIDTextView;
    TextView kmStartTextView;
    EditText kmEndEditText;
    TextView dateStartTextView;
    TextView dateEndTextView;
    Spinner fuelStateSpinner;
    EditText amountFilledEditText;
    TextView priceTextView;
    Button updateCommandButton;


    private String dateEndString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_command);

        // Get the ID of the command
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            commandID = extras.getLong("CommandID");
        }

        // Get clientID;
        sharedPref = getSharedPreferences( getString(R.string.preference_login),
                Context.MODE_PRIVATE);
        clientID = sharedPref.getLong(getString(R.string.client_id), 31);

        // Initialize UI elements
        commandIDTextView = findViewById(R.id.text_view_command_id);
        carIDTextView = findViewById(R.id.textView_command_car_id);
        kmStartTextView = findViewById(R.id.text_view_command_start_km);
        kmEndEditText = findViewById(R.id.edit_text_command_end_km);
        dateStartTextView = findViewById(R.id.text_view_command_start_date);
        dateEndTextView = findViewById(R.id.text_view_command_end_date);
        fuelStateSpinner = findViewById(R.id.spinner_command_fuel_state);
        amountFilledEditText = findViewById(R.id.edit_text_command_amount_filled);
        priceTextView = findViewById(R.id.textView_command_price);
        updateCommandButton = findViewById(R.id.button_update_command);

        ArrayAdapter<FUEL_STATE> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, FUEL_STATE.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelStateSpinner.setAdapter(adapter);
        fuelStateSpinner.setPrompt("Fuel State");
        dateEndString = DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date()).toString();
        dateEndTextView.setText(dateEndString);

        new GetCommand().execute(commandID,clientID);

        updateCommandButton.setOnClickListener(new View.OnClickListener() {
            ContentValues contentValues;

            @Override
            public void onClick(View v) {

                contentValues = new ContentValues();
                contentValues.put(Academy_Const.CommandConst.ID,String.valueOf(command.getCommandId()));
                contentValues.put(Academy_Const.CommandConst.CAR_ID,String.valueOf(command.getCarId()));
                contentValues.put(Academy_Const.CommandConst.CLIENT_ID,String.valueOf(command.getClientId()));
                contentValues.put(Academy_Const.CommandConst.END_RENTING_DATE,String.valueOf(dateEndString));
                contentValues.put(Academy_Const.CommandConst.FUEL_STATE,String.valueOf(fuelStateSpinner.getSelectedItem()));
                contentValues.put(Academy_Const.CommandConst.AMOUNTOfLITERFILLED,amountFilledEditText.getText().toString().trim());
                contentValues.put(Academy_Const.CommandConst.BILL,String.valueOf(0));
                contentValues.put(Academy_Const.CommandConst.END_NUMBER_KILOMETRE,kmEndEditText.toString().trim());
                // TODO: Calculate price
                new Task.closeCommandTask().execute(contentValues);
                startActivity(new Intent(getApplicationContext(),CommandListActivity.class));
                finish();
            }
        });

    }

    private class GetCommand extends AsyncTask<Long,Void,List<Command>>
    {

        @Override
        protected List<Command> doInBackground(Long... longs) {
            return Manager.getCommandByCommandID(longs[0],longs[1]);
        }

        @Override
        protected void onPostExecute(List<Command> commands) {
            command = commands.get(0);
            populateScreen(command);

        }
    }

    private void populateScreen(Command command)
    {
        commandIDTextView.setText(String.valueOf(command.getCommandId()));
        carIDTextView.setText(String.valueOf(command.getCarId()));
        kmStartTextView.setText(String.valueOf(command.getStartNumberKilometre()));
        dateStartTextView.setText(String.valueOf(command.getStartRentingDate()));
        dateEndTextView.setText(String.valueOf(command.getEndRentingDate()));
        priceTextView.setText(String.valueOf(command.getPrice()));

    }
}
