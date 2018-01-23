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
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;

import java.util.ArrayList;

/**
 * Created by binyamin on 22/01/2018.
 */

public class BranchSimpleAdapter extends ArrayAdapter<Branch> {


    public BranchSimpleAdapter(Activity context, ArrayList<Branch> branchArrayList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, branchArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView1 = convertView;

        if(listItemView1 == null) {
            listItemView1 = LayoutInflater.from(getContext()).inflate(
                    R.layout.branch_list_simple_item, parent, false);
        }

        final View  listItemView = listItemView1;

        // Get the {@link currentCar} object located at this position in the list
        Branch currentBranch = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.branchNameTextView);
        TextView distanceTextView = (TextView) listItemView.findViewById(R.id.distanceBranchTextView);

        nameTextView.setText(currentBranch.getBranchName());

        //TODO:Change it
        distanceTextView.setText("47 Km");
        // so that it can be shown in the ListView
        return listItemView;    }
}
