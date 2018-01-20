package com.ephraimhammer.jct.customercarrental.model.backend;

import android.content.ContentValues;

import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.Client;

import java.util.List;

/**
 * Created by binyamin on 17/01/2018.
 */

public interface DB_Manager {

    boolean isClientExist(long id);
    boolean isMatchedPassword(String password, String id);
    long addClient(ContentValues client);

    boolean updateCar(ContentValues car);

    List<Client> getClients();
    List<Branch> getBranchs();
    List<Car> getFreeCars();
    List<Car> getFreeCarsPerBranch(long idBranch);


    List<Car> getFreeCarsByKilometereRange(int range, int sector);

    long addCommand(ContentValues command);
    boolean closeCommand(ContentValues command);
    boolean isCommandClosedWithinTen();

}
