package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

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

public class CarFreeListFragment extends Fragment {


    long branchId;
    int rangeKm = 0;
    int sector = 0;
    boolean isUsedForMainFrag;
    MySql_DBManager Manager;
    SharedPreferences preferences ;
    SEARCH_CAR_TYPE search_car_type;
    SearchView searchView;
    CarSimpleAdapter itemAdapter;
    View view ;


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
                itemAdapter = new CarSimpleAdapter(getActivity(), carModelArrayList);
                ListView listView = getActivity().findViewById(R.id.rootViewCarFreeFragment);
                listView.setAdapter(itemAdapter);
            }
        }
    }
    class FreeCarByRangeTask extends AsyncTask<Integer, Void, List<Car>> {
        @Override
        protected List<Car> doInBackground(Integer... integers) {
            return Manager.getFreeCarsByKilometereRange(integers[0], integers[1]);
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            if (isAdded()) {
                ArrayList<Car> carModelArrayList = new ArrayList<Car>(cars);
                itemAdapter = new CarSimpleAdapter(getActivity(), carModelArrayList);
                ListView listView = (ListView) getActivity().findViewById(R.id.rootViewCarFreeFragment);
                listView.setAdapter(itemAdapter);
            }
        }
    }
    class FreeCarListTask extends AsyncTask<Void, Void, List<Car>> {


        private Boolean isUsedFormainFrag;
        public FreeCarListTask(Boolean bool) {
            this.isUsedFormainFrag = bool;
        }

        @Override
        protected List<Car> doInBackground(Void... voids) {
            return Manager.getFreeCars();
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            if(isAdded()) {
                ArrayList<Car> carModelArrayList = new ArrayList<>(cars);
                itemAdapter = new CarSimpleAdapter(getActivity(), carModelArrayList);
                itemAdapter.setIsusedForMainFrag(isUsedFormainFrag);
                ListView listView = (ListView) view.findViewById(R.id.rootViewCarFreeFragment);
                listView.setAdapter(itemAdapter);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.free_cars_list_fragment, container, false);
        Manager = MySql_DBManager.getInstance();

        // set the sector of the client
        preferences = getContext().getSharedPreferences(
                getString(R.string.preference_login), Context.MODE_PRIVATE);
        sector = preferences.getInt(getContext().getString(R.string.client_sector) , 5);


        // set the search view
        searchView = view.findViewById(R.id.search_view_car);

        setSearchView();


        // BroadcastReceiver
        IntentFilter filter = new IntentFilter("com.example.binyamin.android5778_0445_7734_01.BroadcastReceiver");
        getActivity().registerReceiver(new FreeCarReceiver(), filter);


        // choose according the request wich type of search is it.
        SEARCH_CAR_TYPE searchCarType = (search_car_type);
        switch (search_car_type) {
            case FREE_CARS:
                new FreeCarListTask(isUsedForMainFrag).execute();
                break;
            case FREE_CARS_BY_BRANCH:
                new FreeCarByBranchTask().execute(branchId);
                break;
            case FREE_CARS_BY_RANGE_KM:
                new FreeCarByRangeTask().execute(rangeKm, sector);
            default:
                break;

        }


        return view;
    }

    private void setSearchView()
    {

        if(isUsedForMainFrag) {
            searchView.setVisibility(View.VISIBLE);
        }
        else
        {
            searchView.setVisibility(View.GONE);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                itemAdapter.getFilter().filter(query);
                itemAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}


