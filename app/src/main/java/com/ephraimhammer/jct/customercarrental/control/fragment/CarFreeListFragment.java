package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.adapter.CarAdapter;
import com.ephraimhammer.jct.customercarrental.control.adapter.CarSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 22/01/2018.
 */

public class CarFreeListFragment extends ListFragment {


    long branchId;
    MySql_DBManager Manager;

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.free_cars_list_fragment, container , false);
        Manager = MySql_DBManager.getInstance();

        class FreeCarByBranchTask extends AsyncTask<Long,Void,List<Car>>
        {

            @Override
            protected List<Car> doInBackground(Long... longs) {
                return Manager.getFreeCarsPerBranch(longs[0]);
            }

            @Override
            protected void onPostExecute(List<Car> cars) {
                if (isAdded()) {
                    ArrayList<Car> carModelArrayList = new ArrayList<Car>(cars);
                    CarSimpleAdapter itemAdapter =
                            new CarSimpleAdapter(getActivity(), carModelArrayList);
                    ListView listView = (ListView) getActivity().findViewById(R.id.rootViewCarFreeFragment);
                    listView.setAdapter(itemAdapter);
                }
            }
        }
        new FreeCarByBranchTask().execute(branchId);

        return view;
    }
}
