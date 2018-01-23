package com.ephraimhammer.jct.customercarrental.control.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;

import java.util.ArrayList;

/**
 * Created by binyamin on 21/01/2018.
 */

public class CarAdapter extends ArrayAdapter<Car> {


        public CarAdapter(Activity context, ArrayList<Car> CarArrayList) {
            // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
            // the second argument is used when the ArrayAdapter is populating a single TextView.
            // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
            // going to use this second argument, so it can be any value. Here, we used 0.
            super(context, 0, CarArrayList);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View listItemView1 = convertView;

            if(listItemView1 == null) {
                listItemView1 = LayoutInflater.from(getContext()).inflate(
                        R.layout.car_simple_list_item_frag, parent, false);
            }

            final View  listItemView = listItemView1;

            // Get the {@link currentCar} object located at this position in the list
            Car currentCar = getItem(position);

            // Find the TextView in the list_item.xml layout with the ID version_name






            TextView kmTextView = (TextView) listItemView.findViewById(R.id.kmTextView);
            Switch isFreeSwitch = (Switch) listItemView.findViewById(R.id.isFreeSwitch);
            TextView idTextView = (TextView) listItemView.findViewById(R.id.idtextView);

            // Get the version name from the current Word object and
            // set this text on the name TextView

          kmTextView.setText( Integer.toString(currentCar.getKilometre()));
            idTextView.setText(Integer.toString((int) currentCar.getCarId()));
            isFreeSwitch.setChecked(currentCar.isFree() ? true : false);
            isFreeSwitch.setEnabled(false);


















            // Return the whole list item layout (containing 5 TextViews)
            // so that it can be shown in the ListView
            return listItemView;    }

}
