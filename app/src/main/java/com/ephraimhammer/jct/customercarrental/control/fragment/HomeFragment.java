package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.activity.CarListActivity;
import com.ephraimhammer.jct.customercarrental.control.other.SEARCH_CAR_TYPE;

/**
 * Created by binyamin on 23/01/2018.
 */


public class HomeFragment extends Fragment {

    Button mButtonFreeCarsByBranch;
    Button mButtonFreeCars;
    Button mButtonFreeCarsByRangeKm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container , false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

           /*
        * Initialize UI Elements
        */
        mButtonFreeCarsByBranch = getView().findViewById(R.id.button_free_cars_by_branch);
        mButtonFreeCars = getView().findViewById(R.id.button_free_cars);
        mButtonFreeCarsByRangeKm = getView().findViewById(R.id.button_free_cars_by_rangeKm);

        /*
        * Set OnClickListeners
        */
        mButtonFreeCarsByBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setmButtonFreeCarsByBranch();
                //Intent mCarsPerBranchActivity = new Intent(getActivity(), CarListActivity.class);
                //mCarsPerBranchActivity.putExtra("type", SEARCH_CAR_TYPE.FREE_CARS_BY_BRANCH );
                //startActivity(mCarsPerBranchActivity);
            }
        });
        mButtonFreeCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setmButtonFreeCars();
                //Intent mFreeCars = new Intent(getActivity(), CarListActivity.class);
                //mFreeCars.putExtra("type" , SEARCH_CAR_TYPE.FREE_CARS);
                //startActivity(mFreeCars);
            }
        });
        mButtonFreeCarsByRangeKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setmButtonFreeCarsByRangeKm();
                // Intent mFreeCarsByRangeKm = new Intent(getActivity() , CarListActivity.class );
                //mFreeCarsByRangeKm.putExtra("type", SEARCH_CAR_TYPE.FREE_CARS_BY_RANGE_KM);
                //startActivity(mFreeCarsByRangeKm);
            }
        });
    }

    private void setmButtonFreeCarsByBranch() {
        mButtonFreeCarsByBranch.setBackgroundResource(R.drawable.corner_left_top_right);
        mButtonFreeCars.setBackgroundResource(R.drawable.corner_all);
        mButtonFreeCarsByRangeKm.setBackgroundResource(R.drawable.corner_all);
    }

    private void setmButtonFreeCars(){
        mButtonFreeCarsByBranch.setBackgroundResource(R.drawable.corner_all);
        mButtonFreeCars.setBackgroundResource(R.drawable.corner_left_top_right);
        mButtonFreeCarsByRangeKm.setBackgroundResource(R.drawable.corner_all);

    }
    private void setmButtonFreeCarsByRangeKm(){
        mButtonFreeCarsByBranch.setBackgroundResource(R.drawable.corner_all);
        mButtonFreeCars.setBackgroundResource(R.drawable.corner_all);
        mButtonFreeCarsByRangeKm.setBackgroundResource(R.drawable.corner_left_top_right);

    }
}
