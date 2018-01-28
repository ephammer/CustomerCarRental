package com.ephraimhammer.jct.customercarrental.control.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.Task;
import com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.CarModel;

import org.w3c.dom.Text;

public class AddCommandActivity extends AppCompatActivity {

    long branchId;
    long clientId;
    long carId;
    long carModelId;
    int kilometre;
    Bundle intentExtras ;
    String startCommandDate;
    MySql_DBManager Manager;
    Button addCommandButton;
    AlertDialog alertDialog;
    SharedPreferences preferences ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_command);

        Manager = MySql_DBManager.getInstance();
        startCommandDate =  DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date()).toString();
        intentExtras =  getIntent().getExtras();
        branchId = (long) intentExtras.get(Academy_Const.BranchConst.ID);
        carId = (long) intentExtras.get("carId");
        kilometre = (int) intentExtras.get(Academy_Const.CarConst.KILOMETRE);
        carModelId = (long) intentExtras.get("carModelId");

        addCommandButton = (Button)findViewById(R.id.addCommandbutton);

        alertDialog = new AlertDialog.Builder(this).create();

        preferences =getApplicationContext().getSharedPreferences(
                getString(R.string.preference_login), Context.MODE_PRIVATE);






        addCommandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        //TODO:SHARED PREFERENCE.
        clientId = preferences.getInt(getApplicationContext().getString(R.string.client_id), 37);;

        new CarModelByIdTask().execute(carModelId);


    }


    class  CarModelByIdTask extends AsyncTask<Long, Void, CarModel>
    {


        @Override
        protected CarModel doInBackground(Long... longs) {
            return  Manager.getCarModelById(longs[0]);
        }

        @Override
        protected void onPostExecute(CarModel carModel) {
            TextView mBrandTextView = findViewById(R.id.brandtextView);
            TextView mKilometreTextView = findViewById(R.id.kmTextView);
            TextView mPriceTxtView =findViewById(R.id.priceTxtView);
            TextView mFuelTxtView = findViewById(R.id.fueltextView);
            TextView mA_CTxtView = findViewById(R.id.ACtextView);
            TextView mSmallLugageTxtView = findViewById(R.id.BigLuggtextView);
            TextView mBigLugageTxtView = findViewById(R.id.smalLugTextView);
            TextView mPassengersTxtView = findViewById(R.id.passengerTextView);

            mBrandTextView.setText(carModel.getModelCompanyName().toString());
            mKilometreTextView.setText(String.valueOf(kilometre));
            mPriceTxtView.setText(String.valueOf(carModel.getPriceDay())+ " $ /Day");
            mFuelTxtView.setText(String.valueOf(carModel.getModelMotorVolume())+ " L");

            mA_CTxtView.setText(carModel.isAirC() ? "Yes" : "No");


            switch (CarModel.getLuggageCompartment()) {
                case SMALL:
                    mSmallLugageTxtView.setText("2");
                    mBigLugageTxtView.setText("1");
                    break;
                case MID:
                    mSmallLugageTxtView.setText("4");
                    mBigLugageTxtView.setText("2");
                    break;
                case BIG:
                    mSmallLugageTxtView.setText("6");
                    mBigLugageTxtView.setText("5");
                    break;

                case HUGE:
                    mSmallLugageTxtView.setText("8");
                    mBigLugageTxtView.setText("7");
                    break;
            }

            mPassengersTxtView.setText(carModel.getPassengers().toString().toLowerCase());



            alertDialog.setMessage("Push Ok to rent " + carModel.getModelCompanyName() + "  " + carModel.getModelName() );
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            addCommand();
                            dialog.dismiss();
                        }
                    });


        }

    }

    private  void addCommand()
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Academy_Const.CommandConst.CLIENT_ID , clientId);
        contentValues.put(Academy_Const.CommandConst.CAR_ID , carId);
        contentValues.put(Academy_Const.CommandConst.START_NUMBER_KILOMETRE, kilometre);
        contentValues.put(Academy_Const.CommandConst.START_RENTING_DATE , startCommandDate);

        new Task.addCommandTask().execute(contentValues);
    }


}
