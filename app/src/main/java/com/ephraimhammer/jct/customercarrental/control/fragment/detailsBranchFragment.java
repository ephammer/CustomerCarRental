package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;

/**
 * Created by binyamin on 22/01/2018.
 */

public class detailsBranchFragment extends Fragment {

    private Branch branch;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.details_branch_fragment, container , false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        TextView mNameBranchTxtView = (TextView)view.findViewById(R.id.detailBranchNameTextView);
        TextView mCityBranchTxtView = (TextView)view.findViewById(R.id.textView_branch_city);
        TextView mStreetBranchTxtView = (TextView)view.findViewById(R.id.textView_branch_street);
        TextView mStreetNBBranchTxtView = (TextView)view.findViewById(R.id.textView_branch_street_number);
        TextView mParkingPlace = (TextView)view.findViewById(R.id.textView_amount_parking_place);

        mCityBranchTxtView.setText(branch.getBranchCity());
        mNameBranchTxtView.setText(branch.getBranchName());
        mStreetNBBranchTxtView.setText(String.valueOf(branch.getBranchStreetNumber()));
        mStreetBranchTxtView.setText(branch.getBranchStreet());
        mParkingPlace.setText(String.valueOf(branch.getBranchAmountParkingPlace()));



        super.onViewCreated(view, savedInstanceState);
    }
}
