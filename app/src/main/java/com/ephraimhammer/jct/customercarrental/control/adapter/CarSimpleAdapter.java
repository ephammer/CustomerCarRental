package com.ephraimhammer.jct.customercarrental.control.adapter;

import android.app.Activity;
import android.os.AsyncTask;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.COMUNICATE_BTWN_FRAG;
import com.ephraimhammer.jct.customercarrental.control.other.IsAbleToCommunicateFragment;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.CarModel;

import java.util.ArrayList;

/**
 * Created by binyamin on 25/01/2018.
 */

public class CarSimpleAdapter extends ArrayAdapter<Car> {

    IsAbleToCommunicateFragment isAbleToCommunicateFragment;
    MySql_DBManager Manager ;

    public CarSimpleAdapter(Activity context, ArrayList<Car> CarArrayList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, CarArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View mlistItemView = convertView;

        if(mlistItemView == null) {
            mlistItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.car_list_simple_item, parent, false);
        }

        final Car car = getItem(position);
        Manager = MySql_DBManager.getInstance();

        final View finalMlistItemView = mlistItemView;

        class  CarModelByIdTask extends AsyncTask<Long, Void, CarModel>
        {


            @Override
            protected CarModel doInBackground(Long... longs) {
                return  Manager.getCarModelById(longs[0]);
            }

            @Override
            protected void onPostExecute(CarModel carModel) {

                if(carModel != null) {
                    TextView mClasseTextView = finalMlistItemView.findViewById(R.id.classe_textView);
                    TextView mPriceTxtView = finalMlistItemView.findViewById(R.id.price_textView);
                    TextView mBrandTextView = finalMlistItemView.findViewById(R.id.brand_textView);
                    TextView mNameTextView = finalMlistItemView.findViewById(R.id.name_textView);

                    mClasseTextView.setText(carModel.getClasse().toString());
                    mPriceTxtView.setText(String.valueOf(carModel.getPriceDay()) + " $/Day");
                    mBrandTextView.setText(carModel.getModelCompanyName());
                    mNameTextView.setText(carModel.getModelName());
                }

            }

        }

        new CarModelByIdTask().execute(car.getTypeModelID());





        mlistItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAbleToCommunicateFragment =(IsAbleToCommunicateFragment)getContext();
                isAbleToCommunicateFragment.sendData(COMUNICATE_BTWN_FRAG.CAR_SIMPLE_LIST_TO_CAR_DETAIL_ACTIVITY
                , car);
            }
        });






        return  mlistItemView;
    }
}
