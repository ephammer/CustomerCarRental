package com.ephraimhammer.jct.customercarrental.model.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const;
import com.ephraimhammer.jct.customercarrental.model.backend.DB_Manager;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.CarModel;
import com.ephraimhammer.jct.customercarrental.model.entities.Client;
import com.ephraimhammer.jct.customercarrental.model.entities.Command;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 17/01/2018.
 */

public class MySql_DBManager implements DB_Manager {
    private static final MySql_DBManager ourInstance = new MySql_DBManager();

    public static MySql_DBManager getInstance() {
        return ourInstance;
    }

    private String WEB_URL = "http://obinyami.vlab.jct.ac.il/Academy";
    private String SLASH = "/";

    private MySql_DBManager() {
    }

    @Override
    public boolean isClientExist(long id) {
        List<Client> clientList = getClients();

        for (Client c : clientList) {
            if (c.getClientId() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean isMatchedPassword(Context context, String password, String id) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_login), Context.MODE_PRIVATE);

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("mailAddress", id);
        contentValues1.put("password", password);

        List<Client> result = new ArrayList<>();
        try {
            String str = PHPtools.POST(WEB_URL + SLASH + "getClientByPasswordAndMail.php", contentValues1);
            JSONArray array = new JSONObject(str).getJSONArray("client");

            JSONObject jsonObject;
            ContentValues contentValues;
            Client client;

            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                contentValues = PHPtools.JsonToContentValues(jsonObject);
                client = Academy_Const.ContentValuesToClient(contentValues);

                result.add(client);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putLong(context.getString(R.string.client_id), client.getClientId()).apply();
                editor.putString(context.getString(R.string.client_name), client.getFirstName() + " " + client.getLastName()).apply();
                editor.putString(context.getString(R.string.client_email), client.getMailAdress()).apply();


            }
            return !(result.isEmpty());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public long addClient(ContentValues client) {

        try {
            String result = PHPtools.POST(WEB_URL + SLASH + "add_client.php", client);
            long id = Long.parseLong(result);

            //Log.i("addClient: " , result);
            return id;


        } catch (Exception e) {
            Log.e("addClientException: \n", e.toString());
            return -1;
        }
    }

    @Override
    public boolean updateCarOnCloseCommand(ContentValues updateCar) {
        try {
            String result = PHPtools.POST(WEB_URL + SLASH + "update_carCloseCommand.php", updateCar);
            long id = Long.parseLong(result);

            //Log.i("addClient: " , result);
            return true;

        } catch (Exception e) {
            Log.e("closeCommandExcept: \n", e.toString());
            return false;
        }
    }

    public boolean updateCarOnOpenCommand(ContentValues updateCar) {
        try {
            String result = PHPtools.POST(WEB_URL + SLASH + "update_carOpenCommand.php", updateCar);
            long id = Long.parseLong(result);

            //Log.i("addClient: " , result);
            return true;

        } catch (Exception e) {
            Log.e("closeCommandExcept: \n", e.toString());
            return false;
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> result = new ArrayList<>();
        try {
            String str = PHPtools.GET(WEB_URL + SLASH + "getClientList.php");
            JSONArray array = new JSONObject(str).getJSONArray("cilents");

            JSONObject jsonObject;
            ContentValues contentValues;
            Client client;

            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                contentValues = PHPtools.JsonToContentValues(jsonObject);
                client = Academy_Const.ContentValuesToClient(contentValues);

                result.add(client);


            }
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Branch> getBranchs() {

        List<Branch> result = new ArrayList<>();
        try {
            String str = PHPtools.GET(WEB_URL + SLASH + "getBranchList.php");
            JSONArray array = new JSONObject(str).getJSONArray("branch");

            JSONObject jsonObject;
            ContentValues contentValues;
            Branch branch;

            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                contentValues = PHPtools.JsonToContentValues(jsonObject);
                branch = Academy_Const.ContentValuesToBranch(contentValues);

                result.add(branch);

            }
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Car> getFreeCars() {
        List<Car> result = new ArrayList<>();


        try {
            String str = PHPtools.GET(WEB_URL + SLASH + "getFreeCars.php");
            JSONArray array = new JSONObject(str).getJSONArray("car");

            JSONObject jsonObject;
            ContentValues contentValues1;
            Car car;

            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                contentValues1 = PHPtools.JsonToContentValues(jsonObject);
                car = Academy_Const.ContentValuesToCar(contentValues1);

                result.add(car);

            }
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Car> getFreeCarsPerBranch(long idBranch) {
        List<Car> result = new ArrayList<>();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Academy_Const.BranchConst.ID, idBranch);

        try {
            String str = PHPtools.POST(WEB_URL + SLASH + "getFreeCarByBranchId.php", contentValues);
            JSONArray array = new JSONObject(str).getJSONArray("car");

            JSONObject jsonObject;
            ContentValues contentValues1;
            Car car;

            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                contentValues1 = PHPtools.JsonToContentValues(jsonObject);
                car = Academy_Const.ContentValuesToCar(contentValues1);

                result.add(car);

            }
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override

    // the first paramaeter is rangeKm: the  range in  Kilometers the client is looking for.
    // the second parameter is sector: the current sector of the current client.
    public List<Car> getFreeCarsByKilometereRange(int rangeKm, int sector) {

        // One sector is 10 KM.
        int numberOfSector = rangeKm / 10;

        // the min is 0 , the max is 20.
        int minRange = (sector - numberOfSector > 0) ? sector - numberOfSector : 0;
        int maxRange = (sector + numberOfSector > 20) ? sector + numberOfSector : 20;

        ContentValues contentValues = new ContentValues();

        contentValues.put("minRange", minRange);
        contentValues.put("maxRange", maxRange);

        List<Car> result = new ArrayList<>();
        try {
            String str = PHPtools.POST(WEB_URL + SLASH + "getFreeCarsByKilometerRange.php", contentValues);
            JSONArray array = new JSONObject(str).getJSONArray("car");

            JSONObject jsonObject;
            ContentValues contentValues1;
            Car car;

            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                contentValues1 = PHPtools.JsonToContentValues(jsonObject);
                car = Academy_Const.ContentValuesToCar(contentValues1);

                result.add(car);

            }
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;


    }

    @Override
    public CarModel getCarModelById(long id) {

        CarModel carModel = new CarModel();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Academy_Const.CarModelConst.ID, id);
        try {
            String str = PHPtools.POST(WEB_URL + SLASH + "getCarModelById.php", contentValues);
            JSONArray array = new JSONObject(str).getJSONArray("carModel");

            JSONObject jsonObject;
            ContentValues contentValues1;


            jsonObject = array.getJSONObject(0);
            contentValues1 = PHPtools.JsonToContentValues(jsonObject);
            carModel = Academy_Const.ContentValuesToCarModel(contentValues1);

            return carModel;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return carModel;
    }

    @Override
    public long addCommand(ContentValues command) {
        try {
            String result = PHPtools.POST(WEB_URL + SLASH + "add_command.php", command);
            long id = Long.parseLong(result);

            //Log.i("addClient: " , result);
            return id;

        } catch (Exception e) {
            Log.e("addCommandException: \n", e.toString());
            return -1;
        }
    }

    @Override
    public boolean closeCommand(ContentValues command) {
        try {
            String result = PHPtools.POST(WEB_URL + SLASH + "update_command.php", command);
            long id = Long.parseLong(result);

            //Log.i("addClient: " , result);
            return true;

        } catch (Exception e) {
            Log.e("closeCommandExcept: \n", e.toString());
            return false;
        }
    }

    @Override
    public List<Command> getCommandByClient(long idClient) {

        Command command;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Academy_Const.CommandConst.CLIENT_ID, idClient);


        List<Command> result = new ArrayList<>();
        try {
            String str = PHPtools.POST(WEB_URL + SLASH + "getCommandByClientId.php", contentValues);
            JSONArray array = new JSONObject(str).getJSONArray("command");

            JSONObject jsonObject;
            ContentValues contentValues1;

            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                contentValues1 = PHPtools.JsonToContentValues(jsonObject);
                command = Academy_Const.ContentValuesToCommand(contentValues1);

                result.add(command);

            }
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Branch getBranchById(long id) {
        Branch branch = new Branch();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Academy_Const.CarModelConst.ID, id);
        try {
            String str = PHPtools.POST(WEB_URL + SLASH + "getBranchById.php", contentValues);
            JSONArray array = new JSONObject(str).getJSONArray("branch");

            JSONObject jsonObject;
            ContentValues contentValues1;


            jsonObject = array.getJSONObject(0);
            contentValues1 = PHPtools.JsonToContentValues(jsonObject);
            branch = Academy_Const.ContentValuesToBranch(contentValues1);

            return branch;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return branch;
    }

    @Override
    public List isCommandClosedWithinTen() {
        List<Car> result = new ArrayList<>();


        try {
            String str = PHPtools.GET(WEB_URL + SLASH + "isCommandClosedWithinTen.php");
            JSONArray array = new JSONObject(str).getJSONArray("car");

            JSONObject jsonObject;
            ContentValues contentValues1;
            Car car;

            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                contentValues1 = PHPtools.JsonToContentValues(jsonObject);
                car = Academy_Const.ContentValuesToCar(contentValues1);

                result.add(car);

            }
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Command> getCommandByCommandID(long idCommand, long idClient) {

        Command command;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Academy_Const.CommandConst.CLIENT_ID, idClient);
        contentValues.put(Academy_Const.CommandConst.ID, idCommand);


        List<Command> result = new ArrayList<>();
        try
        {
            String str = PHPtools.POST(WEB_URL + SLASH +"getCommandByCommandId.php" , contentValues);
            JSONArray array = new JSONObject(str).getJSONArray("command");

            JSONObject jsonObject;
            ContentValues contentValues1;

            for (int i = 0 ; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                contentValues1 = PHPtools.JsonToContentValues(jsonObject);
                command = Academy_Const.ContentValuesToCommand(contentValues1);

                result.add(command);

            }
            return result;


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }


}
