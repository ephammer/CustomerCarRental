package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ephraimhammer.jct.customercarrental.control.activity.MapActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;

/**
 * Created by binyamin on 22/01/2018.
 */

public class DetailBranchFragment extends Fragment  {

    private Long branch;
    private MySql_DBManager Manager;

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    class BranchByIdTask extends AsyncTask<Void, Void , Branch>
    {

        View view ;

        public BranchByIdTask(View view) {
            this.view = view;
        }

        @Override
        protected Branch doInBackground(Void... voids) {
            return Manager.getBranchById(branch);
        }

        @Override
        protected void onPostExecute(final Branch branch) {
            TextView mNameBranchTxtView = (TextView)view.findViewById(R.id.detailBranchNameTextView);
            TextView mCityBranchTxtView = (TextView)view.findViewById(R.id.textView_branch_city);
            TextView mStreetBranchTxtView = (TextView)view.findViewById(R.id.textView_branch_street);
            TextView mStreetNBBranchTxtView = (TextView)view.findViewById(R.id.textView_branch_street_number);
            TextView mParkingPlace = (TextView)view.findViewById(R.id.textView_amount_parking_place);
            TextView mMapsLink = (TextView)view.findViewById(R.id.mapTxtView);

            mCityBranchTxtView.setText(branch.getBranchCity());
            mNameBranchTxtView.setText(branch.getBranchName());
            mStreetNBBranchTxtView.setText(String.valueOf(branch.getBranchStreetNumber()));
            mStreetBranchTxtView.setText(branch.getBranchStreet());
            mParkingPlace.setText(String.valueOf(branch.getBranchAmountParkingPlace()));

            mMapsLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapLink = new Intent(getActivity() , MapActivity.class);
                    mapLink.putExtra("numberStreet" , branch.getBranchStreetNumber() );
                    mapLink.putExtra("street" , branch.getBranchStreet());
                    mapLink.putExtra("city" , branch.getBranchCity());
                    startActivity(mapLink);
                }
            });


        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.details_branch_fragment, container , false);
        Manager = MySql_DBManager.getInstance();
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        new BranchByIdTask(view).execute();

        super.onViewCreated(view, savedInstanceState);
    }
}


