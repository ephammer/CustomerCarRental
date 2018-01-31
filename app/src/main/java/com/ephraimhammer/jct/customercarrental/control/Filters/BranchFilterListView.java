package com.ephraimhammer.jct.customercarrental.control.Filters;

import android.content.Context;
import android.widget.Filter;

import com.ephraimhammer.jct.customercarrental.control.adapter.BranchSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.control.adapter.CarSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 31/01/2018.
 */

public class BranchFilterListView extends Filter {

    List<Branch> branches;
    Context mCtxt;

    MySql_DBManager Manager;
    BranchSimpleAdapter branchSimpleAdapter;

    public BranchFilterListView(List<Branch> branches, Context mCtxt, BranchSimpleAdapter branchSimpleAdapter) {
        this.branches = branches;
        this.mCtxt = mCtxt;
        this.branchSimpleAdapter = branchSimpleAdapter;
        Manager = MySql_DBManager.getInstance();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();


        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            results.values = branches;
            results.count = branches.size();
        }
        else {
            // implement the logic of the filter:
            // the car has idCarmodel, CarModel has a name , we want to display
            // all the car that has the carModel.getName == constraint
            // so looking for each car if it's attached to a carModel that has the constraint.

            List<Branch> filteredListBranch = new ArrayList<Branch>();

            for (Branch b: branches) {

                if(b.getBranchCity().toUpperCase().startsWith(
                        constraint.toString().toUpperCase()) )
                    filteredListBranch.add(b);


            }
            results.values = filteredListBranch;
            results.count = filteredListBranch.size();


        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        if (results.count ==  0)
            branchSimpleAdapter.notifyDataSetInvalidated();
        else
        {
            branches.removeAll(branches);
            for (Branch b: (List<Branch>)results.values) {
                branches.add(b);
            }
            branchSimpleAdapter.notifyDataSetChanged();
        }

    }
}
