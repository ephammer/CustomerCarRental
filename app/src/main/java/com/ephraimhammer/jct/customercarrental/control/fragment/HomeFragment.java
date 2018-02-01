package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.COMUNICATE_BTWN_FRAG;
import com.ephraimhammer.jct.customercarrental.control.other.IsAbleToCommunicateFragment;
import com.ephraimhammer.jct.customercarrental.control.other.SEARCH_CAR_TYPE;

/**
 * Created by binyamin on 23/01/2018.
 */


public class HomeFragment extends Fragment {




    Button mButtonFreeCarsByRangeKm;
    int rangeKm;
    SearchView searchView;

     IsAbleToCommunicateFragment isAbleToCommunicateFragment;



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
        mButtonFreeCarsByRangeKm = getView().findViewById(R.id.button_free_cars_by_rangeKm);

        isAbleToCommunicateFragment = (IsAbleToCommunicateFragment)(getActivity());
        searchView = (SearchView)getView().findViewById(R.id.search_view_home);




        /*
        * Set OnClickListeners
        */


        mButtonFreeCarsByRangeKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                Toast.makeText(getActivity(), "Type a number",
                        Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                    rangeKm = Integer.valueOf(query);
                    loadFragmentList();
                    return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    private void loadFragmentList()
    {

        CarFreeListFragment carFreeListFragment =  new CarFreeListFragment() ;
        carFreeListFragment.setRangeKm(rangeKm);
        carFreeListFragment.setSearch_car_type(SEARCH_CAR_TYPE.FREE_CARS_BY_RANGE_KM);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, carFreeListFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }




}
