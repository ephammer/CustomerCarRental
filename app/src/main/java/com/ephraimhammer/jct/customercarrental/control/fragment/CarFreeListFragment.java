package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.ListFragment;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.adapter.CarSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.control.other.FreeCarReceiver;
import com.ephraimhammer.jct.customercarrental.control.other.SEARCH_CAR_TYPE;
import com.ephraimhammer.jct.customercarrental.control.other.Task;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 22/01/2018.
 */

public class CarFreeListFragment extends ListFragment {


    long branchId;
    int rangeKm = 0;
    int sector = 0;
    boolean isUsedForMainFrag;
    MySql_DBManager Manager;

    SEARCH_CAR_TYPE search_car_type;


    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public void setRangeKm(int rangeKm) {
        this.rangeKm = rangeKm;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    public void setSearch_car_type(SEARCH_CAR_TYPE search_car_type) {
        this.search_car_type = search_car_type;
    }

    public void setUsedForMainFrag(boolean usedForMainFrag) {
        isUsedForMainFrag = usedForMainFrag;
    }


    class FreeCarByBranchTask extends AsyncTask<Long, Void, List<Car>> {

        @Override
        protected List<Car> doInBackground(Long... longs) {
            return Manager.getFreeCarsPerBranch(longs[0]);
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            if (isAdded()) {
                ArrayList<Car> carModelArrayList = new ArrayList<>(cars);
                CarSimpleAdapter itemAdapter =
                        new CarSimpleAdapter(getActivity(), carModelArrayList);
                ListView listView = getActivity().findViewById(R.id.rootViewCarFreeFragment);
                listView.setAdapter(itemAdapter);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.free_cars_list_fragment, container, false);
        Manager = MySql_DBManager.getInstance();

        // BroadcastReceiver
        IntentFilter filter = new IntentFilter("com.example.binyamin.android5778_0445_7734_01.BroadcastReceiver");
        getActivity().registerReceiver(new FreeCarReceiver(), filter);


        SEARCH_CAR_TYPE searchCarType = (search_car_type);
        switch (search_car_type) {
            case FREE_CARS:
                new Task.FreeCarListTask(getContext(), isUsedForMainFrag, view).execute();
                break;
            case FREE_CARS_BY_BRANCH:
                new FreeCarByBranchTask().execute(branchId);
                break;
            case FREE_CARS_BY_RANGE_KM:
                new Task.FreeCarByRangeTask(getActivity()).execute(rangeKm, sector);
            default:
                break;

        }


        return view;
    }
}
