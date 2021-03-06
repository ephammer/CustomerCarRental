package com.ephraimhammer.jct.customercarrental.control.other;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.activity.EndCommandActivity;
import com.ephraimhammer.jct.customercarrental.control.adapter.BranchSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.control.adapter.CarAdapter;
import com.ephraimhammer.jct.customercarrental.control.adapter.CommandAdapter;
import com.ephraimhammer.jct.customercarrental.control.adapter.CarSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.control.adapter.CarSimpleAdapter;
import com.ephraimhammer.jct.customercarrental.control.adapter.CommandAdapter;
import com.ephraimhammer.jct.customercarrental.model.datasource.MySql_DBManager;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.COMMAND_STATE;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.Client;
import com.ephraimhammer.jct.customercarrental.model.entities.Command;
import com.ephraimhammer.jct.customercarrental.model.entities.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 21/11/2017.
 * <p>
 * This class manage ALL the Task used in the controller.
 * We use a task each time we need access to the Data section.
 * So each access to the data is done through a separate thread from the UIThread.
 */
public class Task {
    // get instance of the list_dbManager who manage the data.


    static MySql_DBManager Manager = MySql_DBManager.getInstance();

    public Task() {

    }


    //static List_DBManager list_dbManager  = List_DBManager.getInstance();
    //static MySQL_D list_dbManager mySQL_d list_dbManager = MySQL_D list_dbManager.getInstance();


    //TODO: Change the list_dbManager to be SQL list_dbManager.
    // TODO:Add also php file in the cloud for post and get method.


    public static class ClientListTask extends AsyncTask<Void, Void, List<Client>> {

        private Activity context;

        public ClientListTask(Activity context) {
            this.context = context;
        }

        @Override
        protected List<Client> doInBackground(Void... voids) {
            return Manager.getClients();
        }


    }

    public static class AddClientTask extends AsyncTask<ContentValues, Void, Long> {
        private Context context;

        public AddClientTask(Context context) {
            this.context = context;

        }

        @Override
        protected Long doInBackground(ContentValues... contentValues) {

            if (contentValues[0] != null)
                return Manager.addClient(contentValues[0]);

            else
                return null;
        }

        @Override
        protected void onPostExecute(Long idClient) {
            if (idClient != null)


                Toast.makeText(context, "Client IdNumber: " + idClient.toString() +
                                " created",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Error adding client",
                        Toast.LENGTH_SHORT).show();
        }


    }


    public static class closeCommandTask extends AsyncTask<ContentValues, Void, Boolean> {

        @Override
        protected Boolean doInBackground(ContentValues... contentValues) {
            return Manager.closeCommand(contentValues[0]);
        }
    }

    public static class addCommandTask extends AsyncTask<ContentValues, Void, Long> {
        @Override
        protected Long doInBackground(ContentValues... contentValues) {
            return Manager.addCommand(contentValues[0]);
        }
    }

    public static class FreeCarByBranchTask extends AsyncTask<Long, Void, List<Car>> {

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
            ListView listView = (ListView) activity.findViewById(R.id.rootViewCarFreeFragment);
            listView.setAdapter(itemAdapter);
        }
    }

    public static class FreeCarByRangeTask extends AsyncTask<Integer, Void, List<Car>> {
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
            CarSimpleAdapter itemAdapter =
                    new CarSimpleAdapter(activity, carModelArrayList);
            ListView listView = (ListView) activity.findViewById(R.id.rootViewCarFreeFragment);
            listView.setAdapter(itemAdapter);
        }
    }


    public static class FreeCarListTask extends AsyncTask<Void, Void, List<Car>> {

        private Context context;
        private Boolean isUsedFormainFrag;
        View rootView;
        public FreeCarListTask(Context context , Boolean bool, View rootview) {

            this.context = context;
            this.isUsedFormainFrag = bool;
            this.rootView = rootview;
        }

        public FreeCarListTask(Context context , Boolean bool) {

            this.context = context;
            this.isUsedFormainFrag = bool;
            this.rootView = ((Activity)context).findViewById(R.layout.free_cars_list_fragment);
        }

        @Override
        protected List<Car> doInBackground(Void... voids) {
            return Manager.getFreeCars();
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            ArrayList<Car> carModelArrayList = new ArrayList<>(cars);
            CarSimpleAdapter itemAdapter =
                    new CarSimpleAdapter((Activity) context, carModelArrayList);
            itemAdapter.setIsusedForMainFrag(isUsedFormainFrag);
            ListView listView = rootView.findViewById(R.id.rootViewCarFreeFragment);
            listView.setAdapter(itemAdapter);
        }
    }




    public static class BranchDetailsListTask extends AsyncTask<Void, Void, List<Branch>> {


        @Override
        protected List<Branch> doInBackground(Void... voids) {
            return Manager.getBranchs();
        }


    }

    public static class IsMatchPasswordTask extends AsyncTask<String, Void, Boolean> {

        Context context;

        IsMatchPasswordTask(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return Manager.isMatchedPassword(context, strings[0], strings[1]);

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

            return Manager.isCommandClosedWithinTen();
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            if (!cars.isEmpty()) {
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("com.example.binyamin.android5778_0445_7734_01.BroadcastReceiver");
                context.sendBroadcast(broadcastIntent);
                Log.d("ReservedCarUpdateTask", "BroadCast Send");
            }
            Log.d("ReservedCarUpdateTask", "No new reservation");

        }
    }

    public static class CommandListTask extends AsyncTask<Void, Void, List<Command>> {
        Context context;
        SharedPreferences sharedPref;

        public CommandListTask(Context context) {
            this.context = context;
            sharedPref = context.getSharedPreferences(
                    context.getString(R.string.preference_login), Context.MODE_PRIVATE);
        }

        @Override
        protected List<Command> doInBackground(Void... voids) {
            long id = sharedPref.getLong(context.getString(R.string.client_id), 31);
            Log.d("CommandListTask: ",String.valueOf(id));
            return Manager.getCommandByClient(id);
        }

        @Override
        protected void onPostExecute(List<Command> commands) {
            final ArrayList<Command> commandArrayList = new ArrayList<>(commands);
            CommandAdapter commandAdapter = new CommandAdapter((Activity) context, commandArrayList);
            ListView listView = ((Activity) context).findViewById(R.id.list_view_command);
            listView.setAdapter(commandAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Command command = commandArrayList.get(position);
                    if(command.getCommandState() == COMMAND_STATE.OPEN) {
                        Intent intent = new Intent(context, EndCommandActivity.class);
                        intent.putExtra("CommandID", command.getCommandId());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }


}

