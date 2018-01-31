package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.adapter.BranchSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.control.other.Task;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 22/01/2018.
 */

public class BranchListFragment extends Fragment {

    private SearchView searchView;
    BranchSimpleAdapter itemAdapter;
    MySql_DBManager Manager;


    class BranchSimpleListTask extends AsyncTask<Void, Void, List<Branch>> {


        @Override
        protected List<Branch> doInBackground(Void... voids) {
            return Manager.getBranchs();
        }

        @Override
        protected void onPostExecute(List<Branch> branches) {
            if(isAdded()) {
                ArrayList<Branch> branchArrayList = new ArrayList<>(branches);

                itemAdapter = new BranchSimpleAdapter(getActivity(), branchArrayList);
                ListView listView = (ListView) getActivity().findViewById(R.id.rootViewBranchFragment);
                listView.setAdapter(itemAdapter);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.branch_list_fragment, container, false);

        Manager = MySql_DBManager.getInstance();
        searchView = view.findViewById(R.id.search_view_branch);
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
        
        setSuggestionSearchView();
        new BranchSimpleListTask().execute();


        return view;
    }

    private void setSuggestionSearchView() {

    }


}
