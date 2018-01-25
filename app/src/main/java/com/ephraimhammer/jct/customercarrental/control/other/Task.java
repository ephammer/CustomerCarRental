package com.ephraimhammer.jct.customercarrental.control.other;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.adapter.BranchSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.control.adapter.CarAdapter;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.Client;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by binyamin on 21/11/2017.
 *
 * This class manage ALL the Task used in the controller.
 * We use a task each time we need access to the Data section.
 * So each access to the data is done through a separate thread from the UIThread.
 */
public class Task
{
    // get instance of the list_dbManager who manage the data.



    static MySql_DBManager Manager = MySql_DBManager.getInstance();

    public Task() {

}

    //static List_DBManager list_dbManager  = List_DBManager.getInstance();
    //static MySQL_D list_dbManager mySQL_d list_dbManager = MySQL_D list_dbManager.getInstance();








    //TODO: Change the list_dbManager to be SQL list_dbManager.
    // TODO:Add also php file in the cloud for post and get method.



    public static class ClientListTask extends AsyncTask< Void, Void , List<Client>> {

        private Activity context;

        public ClientListTask(Activity context) {
            this.context = context;
        }

        @Override
        protected List<Client> doInBackground(Void... voids) {
            return Manager.getClients();
        }


    }

    public static    class AddClientTask extends AsyncTask<ContentValues , Void , Long>
    {
        private Context context;
        public AddClientTask(Context context) {
            this.context = context;

        }

        @Override
        protected Long doInBackground(ContentValues... contentValues) {

            if(contentValues[0] != null)
                return Manager.addClient(contentValues[0]);

            else
                return null;
        }

        @Override
        protected void onPostExecute(Long idClient) {
            if(idClient != null)


                Toast.makeText( context, "Client IdNumber: " + idClient.toString() +
                                " created",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Error adding client",
                        Toast.LENGTH_SHORT).show();
        }


    }


    public static class closeCommandTask extends AsyncTask<ContentValues,Void,Boolean>
    {

        @Override
        protected Boolean doInBackground(ContentValues... contentValues) {
            return Manager.closeCommand(contentValues[0]);
        }
    }
    public static class addCommandTask extends AsyncTask<ContentValues, Void , Long>
    {
        @Override
        protected Long doInBackground(ContentValues... contentValues) {
            return Manager.addCommand(contentValues[0]);
        }
    }
    public static class FreeCarByBranchTask extends AsyncTask<Long,Void,List<Car>>
    {

        Activity activity;

        public FreeCarByBranchTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected List<Car> doInBackground(Long... longs) {
            return Manager.getFreeCarsPerBranch(longs[0]);
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            ArrayList<Car> carModelArrayList = new ArrayList<Car>(cars);
            CarAdapter itemAdapter =
                    new CarAdapter(activity, carModelArrayList);
            ListView listView = (ListView) activity.findViewById(R.id.rootView);
            listView.setAdapter(itemAdapter);
        }
    }

    public static class FreeCarByRangeTask extends AsyncTask<Integer,Void,List<Car>>
    {
        Activity activity;

        public FreeCarByRangeTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected List<Car> doInBackground(Integer... integers) {
            return Manager.getFreeCarsByKilometereRange(integers[0], integers[1]);
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            ArrayList<Car> carModelArrayList = new ArrayList<Car>(cars);
            CarAdapter itemAdapter =
                    new CarAdapter(activity, carModelArrayList);
            ListView listView = (ListView) activity.findViewById(R.id.rootView);
            listView.setAdapter(itemAdapter);
        }
    }


    public static class  FreeCarListTask extends AsyncTask<Void,Void,List<Car>>
    {
        Activity context;

        public FreeCarListTask(Activity context) {
            this.context = context;
        }

        @Override
        protected List<Car> doInBackground(Void... voids) {
                    return Manager.getFreeCars();
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            ArrayList<Car> carModelArrayList = new ArrayList<Car>(cars);
            CarAdapter itemAdapter =
                    new CarAdapter(context, carModelArrayList);
            ListView listView = (ListView) context.findViewById(R.id.rootView);
            listView.setAdapter(itemAdapter);
        }
    }
    public static class BranchSimpleListTask extends AsyncTask<Void,Void,List<Branch>>
    {
        Activity activity;

        public BranchSimpleListTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected List<Branch> doInBackground(Void... voids) {
            return Manager.getBranchs();
        }

        @Override
        protected void onPostExecute(List<Branch> branches) {
            ArrayList<Branch> branchArrayList = new ArrayList<>(branches);
            BranchSimpleAdapter branchSimpleAdapter =
                    new BranchSimpleAdapter(activity , branchArrayList);
            ListView listView = (ListView) activity.findViewById(R.id.rootViewBranchFragment);
            listView.setAdapter(branchSimpleAdapter);
        }
    }
    public static class BranchDetailsListTask extends  AsyncTask<Void, Void, List<Branch>>
    {


        @Override
        protected List<Branch> doInBackground(Void... voids) {
            return Manager.getBranchs();
        }



    }

    public static class IsMatchPasswordTask extends AsyncTask< String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... strings) {
                return Manager.isMatchedPassword(strings[0] , strings[1]);

        }
    }


    public static class ReservedCarUpdateTask extends AsyncTask<Void, Void, List<Car>> {
        Context context;

        public ReservedCarUpdateTask(Context context) {
            this.context = context;
            doInBackground();
        }

        @Override
        protected List doInBackground(Void... voids) {

            String date = DateFormat.getDateTimeInstance().format(new Date());

            return Manager.isCommandClosedWithinTen(date);
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            if (!cars.isEmpty()) {
                ArrayList<Car> carsArrayList = new ArrayList<>(cars);

                CarAdapter itemAdapter =
                        new CarAdapter((Activity) context, carsArrayList);
                ListView listView = ((Activity) context).findViewById(R.id.rootView);
                listView.setAdapter(itemAdapter);

                Toast.makeText(context, "Car List Updated", Toast.LENGTH_SHORT).show();

            }
        }
    }
}

