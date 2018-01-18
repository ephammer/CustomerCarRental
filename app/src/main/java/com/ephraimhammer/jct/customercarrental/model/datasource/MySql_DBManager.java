package com.ephraimhammer.jct.customercarrental.model.datasource;

import android.content.ContentValues;
import android.util.Log;

import com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const;
import com.ephraimhammer.jct.customercarrental.model.backend.DB_Manager;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.Client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binyamin on 17/01/2018.
 */

class MySql_DBManager implements DB_Manager {
    private static final MySql_DBManager ourInstance = new MySql_DBManager();

    static MySql_DBManager getInstance() {
        return ourInstance;
    }

    private String bb;
    private String WEB_URL = "http://obinyami.vlab.jct.ac.il/Academy";
    private String SLASH = "/";

    private MySql_DBManager() {
    }

    @Override
    public boolean isClientExist( long  id) {
        List <Client> clientList = getClients();

        for (Client c : clientList) {
            if(c.getClientId() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean isMatchedPassword(String password, String id) {

        List <Client> clientList = getClients();

        for (Client c : clientList) {
            if(c.getMailAdress() == id && c.getPassword() == password)
                return true;
        }
        return false;
    }

    @Override
    public long addClient(ContentValues client) {

        try
        {
            String result = PHPtools.POST(WEB_URL + SLASH +"add_client.php", client);
            long id = Long.parseLong(result);

            //Log.i("addClient: " , result);
            return id;



        }
        catch (Exception e )
        {
            Log.e("addClientException: \n" , e.toString());
            return  -1;
        }
    }

    @Override
    public boolean updateCar(ContentValues car) {
        return false;
    }

    @Override
    public List<Client> getClients() {
        List<Client> result = new ArrayList<>();
        try
        {
            String str = PHPtools.GET(WEB_URL +  SLASH +"getClientList.php");
            JSONArray array = new  JSONObject(str).getJSONArray("cilents");

            JSONObject jsonObject;
            ContentValues contentValues;
            Client client;

            for (int i = 0 ; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                contentValues = PHPtools.JsonToContentValues(jsonObject);
                client = Academy_Const.ContentValuesToClient(contentValues);

                result.add(client);



            }
            return result;


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Branch> getBranchs() {

        List<Branch> result = new ArrayList<>();
        try
        {
            String str = PHPtools.GET(WEB_URL + SLASH +"getBranchList.php");
            JSONArray array = new JSONObject(str).getJSONArray("branch");

            JSONObject jsonObject;
            ContentValues contentValues;
            Branch branch;

            for (int i = 0 ; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                contentValues = PHPtools.JsonToContentValues(jsonObject);
                branch = Academy_Const.ContentValuesToBranch(contentValues);

                result.add(branch);



            }
            return result;


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Car> getFreeCars() {
        return null;
    }

    @Override
    public List<Car> getFreeCarsPerBranch(long idBranch) {
        return null;
    }

    @Override
    public List<Car> getFreeCarsByKilometereRange(int range) {
        return null;
    }

    @Override
    public long addCommand(ContentValues command) {
        return 0;
    }

    @Override
    public boolean closeCommand(ContentValues command) {
        return false;
    }

    @Override
    public boolean isCommandClosedWithinTen() {
        return false;
    }
}
