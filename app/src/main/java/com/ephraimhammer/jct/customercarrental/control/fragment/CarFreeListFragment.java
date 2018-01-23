package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ephraimhammer.jct.customercarrental.R;

/**
 * Created by binyamin on 22/01/2018.
 */

public class CarFreeListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.free_cars_list_fragment, container , false);
    }
}
