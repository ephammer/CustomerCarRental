package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.COMUNICATE_BTWN_FRAG;
import com.ephraimhammer.jct.customercarrental.control.other.IsAbleToCommunicateFragment;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.CarModel;

/**
 * Created by binyamin on 28/01/2018.
 */

public class DetailCarFragment extends Fragment {

    private Car car;
    private MySql_DBManager Manager;
    private IsAbleToCommunicateFragment isAbleToCommunicateFragment;



    public void setCar(Car car) {
        this.car = car;
    }

    class  CarModelByIdTask extends AsyncTask<Long, Void, CarModel>
    {


        @Override
        protected CarModel doInBackground(Long... longs) {
            return  Manager.getCarModelById(longs[0]);
        }

        @Override
        protected void onPostExecute(CarModel carModel) {

            TextView mBrandTextView = getActivity().findViewById(R.id.brandtextView);
            TextView mKilometreTextView = getActivity().findViewById(R.id.kmTextView);
            TextView mPriceTxtView = getActivity().findViewById(R.id.priceTxtView);
            TextView mFuelTxtView = getActivity().findViewById(R.id.fueltextView);
            TextView mA_CTxtView = getActivity().findViewById(R.id.ACtextView);
            TextView mSmallLugageTxtView = getActivity().findViewById(R.id.BigLuggtextView);
            TextView mBigLugageTxtView = getActivity().findViewById(R.id.smalLugTextView);
            TextView mPassengersTxtView = getActivity().findViewById(R.id.passengerTextView);
            Button mbuttonClose = getActivity().findViewById(R.id.closeCarButton);

            mBrandTextView.setText(carModel.getModelCompanyName().toString());
            mKilometreTextView.setText(String.valueOf(car.getKilometre()));
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

            mbuttonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isAbleToCommunicateFragment = (IsAbleToCommunicateFragment)getActivity();
                    isAbleToCommunicateFragment.sendData(COMUNICATE_BTWN_FRAG.CLOSE_DETAIL_REDIRECT);

                }
            });



        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.car_item_frag , container, false);
        Manager = MySql_DBManager.getInstance();
        new CarModelByIdTask().execute(car.getTypeModelID());

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
    }
}
