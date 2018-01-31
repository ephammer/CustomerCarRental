package com.ephraimhammer.jct.customercarrental.control.Filters;

import android.content.Context;
import android.widget.Filter;
import android.widget.ListView;

import com.ephraimhammer.jct.customercarrental.control.adapter.CarSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.CarModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 31/01/2018.
 */

public class CarFilterListView extends Filter {

    List<Car> carList;
    Context mCtxt;
    CarModel carModel;
    MySql_DBManager Manager;
    CarSimpleAdapter carSimpleAdapter;

    public CarFilterListView(List<Car> carList, Context mCtxt , CarSimpleAdapter carSimpleAdapter) {
        this.carList = carList;
        this.mCtxt = mCtxt;
        Manager = MySql_DBManager.getInstance();
        this.carSimpleAdapter = carSimpleAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();


        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            results.values = carList;
            results.count = carList.size();
        }
        else {
            // implement the logic of the filter:
            // the car has idCarmodel, CarModel has a name , we want to display
            // all the car that has the carModel.getName == constraint
            // so looking for each car if it's attached to a carModel that has the constraint.

            List<Car> filteredListCar = new ArrayList<Car>();

            for (Car c: carList) {
                carModel = Manager.getCarModelById(c.getTypeModelID());
                if(carModel.getModelName().toUpperCase().startsWith(
                        constraint.toString().toUpperCase()) || carModel.getModelCompanyName().toUpperCase().startsWith(
                        constraint.toString().toUpperCase()))
                    filteredListCar.add(c);


            }
            results.values = filteredListCar;
            results.count = filteredListCar.size();


        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.count ==  0)
            carSimpleAdapter.notifyDataSetInvalidated();
        else
        {
            carList.removeAll(carList);
            for (Car c : (List<Car>)results.values) {
                carList.add(c);
            }
            carSimpleAdapter.notifyDataSetChanged();
        }

    }
}
