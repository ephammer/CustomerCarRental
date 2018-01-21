package com.ephraimhammer.jct.customercarrental.control;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.model.entities.Client;

import java.util.ArrayList;

/**
 * Created by binou on 18/11/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> {

    public ClientAdapter(Activity context, ArrayList<Client> clientArrayList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, clientArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.client_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Client currentClient = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.firstNameTextView);
        TextView lastNameTextView = (TextView) listItemView.findViewById(R.id.LastNameTextView);
        TextView emailTextView = (TextView) listItemView.findViewById(R.id.EmailTextView);
        TextView cCardTextView = (TextView) listItemView.findViewById(R.id.CreditCardTextView);
        TextView phoneTextView = (TextView) listItemView.findViewById(R.id.PhoneTextView);
        TextView sectorTextView = (TextView) listItemView.findViewById(R.id.sectorTextView);

        // Get the version name from the current Word object and
        // set this text on the name TextView
        nameTextView.setText(currentClient.getFirstName());
        lastNameTextView.setText(currentClient.getLastName());
        emailTextView.setText(currentClient.getMailAdress());
        cCardTextView.setText(currentClient.getCreditCardNumber());
        phoneTextView.setText(currentClient.getPhoneNumber());
        //sectorTextView.setText(currentClient.getSector());

        Log.i("SECTOR: ", String.valueOf(currentClient.getSector()));




        // Return the whole list item layout (containing 5 TextViews)
        // so that it can be shown in the ListView
        return listItemView;    }
}
