package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.Task;

/**
 * Created by binyamin on 22/01/2018.
 */

public class branchListFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.branch_list_fragment, container, false);

        new Task.BranchSimpleListTask(getActivity()).execute();



        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


        super.onListItemClick(l, v, position, id);
    }
}
