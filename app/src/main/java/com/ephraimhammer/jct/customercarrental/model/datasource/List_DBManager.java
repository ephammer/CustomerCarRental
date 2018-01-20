package com.ephraimhammer.jct.customercarrental.model.datasource;

import android.content.ContentValues;


import com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const;
import com.ephraimhammer.jct.customercarrental.model.backend.DB_Manager;
import com.ephraimhammer.jct.customercarrental.model.entities.BRAND;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

import static com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const.CarModelConst.COLOR;
import static com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const.CarModelConst.DOOR;
import static com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const.CarModelConst.PASSENGERS;

/**
 * Created by binyamin on 15/11/2017.
 */

public class List_DBManager implements DB_Manager {

    private static final List_DBManager ourInstance = new List_DBManager();

    public static List_DBManager getInstance() {
        return ourInstance;
    }

    private static void initList() {


        Client client = new Client("Bin", "Oliel","065676876","binoliel@",
                "345678765433333","1234",2);
        clientList.add(client);

        Client client1 = new Client("Ephra", "Hammer","065676876","kidon@",
                "345678765433333","1234",2);
        clientList.add(client1);

        Branch branch = new Branch("Paris", "Maubert", 5 , 21, 12);
        branchList.add(branch);

        Branch branch1 = new Branch("Marseille", "Maubert", 5 , 21, 12);
        branchList.add(branch1);



    }

    private List_DBManager() {

    }

    private static List<Branch> branchList;
    private static List<Car> carList;
    private static List<Client> clientList;



    static {
        branchList = new ArrayList<>();
        carList= new ArrayList<>();
        clientList= new ArrayList<>();

        initList();


    }

    public interface Predicate<T> { boolean apply(T type); }

    public static <T> List<T> filter(List<T> col, Predicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T element: col) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public long addClient(ContentValues values) {

        Client client = Academy_Const.ContentValuesToClient(values);
        // Here we are setting up the id of the client ,
        // Thats why we need to update the values.
        client.setClientId(-1);
        values = Academy_Const.ClientToContentValues(client);

        clientList.add(client);
        return client.getClientId();
    }

    @Override
    public boolean updateCar(ContentValues car) {
        return false;
    }


    @Override
    public boolean isMatchedPassword(String password, String id)
    {
        for (Client c : clientList) {
            if(c.getMailAdress() == id && c.getPassword() == password)
                return true;
        }
        return false;
    }


    @Override
    public boolean isClientExist( final long  id) {

        Predicate<Client> predicate = new Predicate<Client>() {
            public boolean apply(Client client) {
                return client.getClientId() == id;
            }


        };
         return  filter( clientList, predicate).isEmpty();


    }

    @Override
    public List<Client> getClients() {
        return clientList;
    }


    @Override
    public List<Branch> getBranchs() {
        return branchList;
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
    public List<Car> getFreeCarsByKilometereRange(int range, int sector) {
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
