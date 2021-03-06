package com.ephraimhammer.jct.customercarrental.control.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.Filters.BranchFilterListView;
import com.ephraimhammer.jct.customercarrental.control.Filters.CarFilterListView;
import com.ephraimhammer.jct.customercarrental.control.other.COMUNICATE_BTWN_FRAG;
import com.ephraimhammer.jct.customercarrental.control.other.IsAbleToCommunicateFragment;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 22/01/2018.
 */

public class BranchSimpleAdapter extends ArrayAdapter<Branch> implements Filterable {

    List<Branch> branches;
    Context mCtxt ;
    BranchFilterListView branchFilterListView;
    IsAbleToCommunicateFragment isAbleToCommunicateFragment;
    int currentSector;
    SharedPreferences preferences;

    public BranchSimpleAdapter(Activity context, ArrayList<Branch> branchArrayList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, branchArrayList);
        branches = branchArrayList;
        mCtxt = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View listItemView1 = (View)convertView;

        if(listItemView1 == null) {
            listItemView1 = LayoutInflater.from(getContext()).inflate(
                    R.layout.branch_list_simple_item, parent, false);
        }

        preferences = getContext().getSharedPreferences(String.valueOf(R.string.preference_login), Context.MODE_PRIVATE);

        currentSector = preferences.getInt(getContext().getString(R.string.client_sector) , 5);
        final View  listItemView = listItemView1;

        // Get the {@link currentCar} object located at this position in the list
        final Branch currentBranch = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.branchNameTextView);
        TextView distanceTextView = (TextView) listItemView.findViewById(R.id.distanceBranchTextView);

        nameTextView.setText(currentBranch.getBranchName());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAbleToCommunicateFragment = (IsAbleToCommunicateFragment)getContext();
                isAbleToCommunicateFragment.sendData(COMUNICATE_BTWN_FRAG.MAIN_BRANCH_LIST_TO_DETAIL_BRANCH_AND_REDIRECT_CAR, currentBranch);
            }
        });



        //TODO:Change it

        int sectr = (int)(Math.random() * 21 );
        int distanceSector = currentSector > sectr ? currentSector - sectr: sectr- currentSector;
        double distanceKm = distanceSector*10;
        distanceTextView.setText(String.valueOf(distanceKm));
        // so that it can be shown in the ListView
        return listItemView;    }

    @NonNull
    @Override
    public Filter getFilter() {
        if(branchFilterListView == null)
            branchFilterListView = new BranchFilterListView(branches , mCtxt , this);
        return branchFilterListView;
    }
}
