package com.ephraimhammer.jct.customercarrental.control.adapter;

import android.content.Context;
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
import com.ephraimhammer.jct.customercarrental.model.entities.Command;

import java.util.ArrayList;

/**
 * Created by kid0n on 27/01/2018.
 */

public class CommandAdapter extends ArrayAdapter<Command> {
    public CommandAdapter(@NonNull Context context, ArrayList<Command> commandArrayList) {
        super(context, 0, commandArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.command_list_item, parent, false);
        }

        // Get the {@link Command} object located at this position in the list
        Command currentCommand = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView commandIDTextView = (TextView) listItemView.findViewById(R.id.text_view_command_id);
        TextView carIDTextView = (TextView) listItemView.findViewById(R.id.textView_command_car_id);
        TextView stateTextView = (TextView) listItemView.findViewById(R.id.text_view_command_state);
        TextView kmStartTextView = (TextView) listItemView.findViewById(R.id.text_view_command_start_km);
        TextView kmEndTextView = (TextView) listItemView.findViewById(R.id.text_view_command_end_km);
        TextView dateStartTextView = (TextView) listItemView.findViewById(R.id.text_view_command_start_date);
        TextView dateEndTextView = (TextView) listItemView.findViewById(R.id.text_view_command_end_date);
        TextView fuelStateTextView = (TextView) listItemView.findViewById(R.id.textView_command_fuel_state);
        TextView amountFilledTextView = (TextView) listItemView.findViewById(R.id.textView_command_amount_filled);
        TextView priceTextView = (TextView) listItemView.findViewById(R.id.textView_command_price);

        // Get the version name from the current Command object and
        // set this text on the name TextView
        commandIDTextView.setText(String.valueOf(currentCommand.getCommandId()));
        carIDTextView.setText(String.valueOf(currentCommand.getCarId()));
        stateTextView.setText(String.valueOf(currentCommand.getCommandState()));
        kmStartTextView.setText(String.valueOf(currentCommand.getStartNumberKilometre()));
        kmEndTextView.setText(String.valueOf(currentCommand.getEndNumberKilometre()));
        dateStartTextView.setText(String.valueOf(currentCommand.getStartRentingDate()));
        dateEndTextView.setText(String.valueOf(currentCommand.getEndRentingDate()));
        fuelStateTextView.setText(String.valueOf(currentCommand.getFuel_state()));
        amountFilledTextView.setText(String.valueOf(currentCommand.getAmountOfLiterFilled()));
        priceTextView.setText(String.valueOf(currentCommand.getPrice()));


        // Return the whole list item layout (containing 5 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
