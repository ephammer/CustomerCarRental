package com.ephraimhammer.jct.customercarrental.model.backend;

import android.content.ContentValues;
import android.content.Context;

import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;
import com.ephraimhammer.jct.customercarrental.model.entities.CarModel;
import com.ephraimhammer.jct.customercarrental.model.entities.Client;
import com.ephraimhammer.jct.customercarrental.model.entities.Command;

import java.util.List;

/**
 * Created by binyamin on 17/01/2018.
 */

public interface DB_Manager {

    boolean isClientExist(long id);
    boolean isMatchedPassword(Context context, String password, String id);
    long addClient(ContentValues client);



    List<Client> getClients();
    List<Branch> getBranchs();
    List<Car> getFreeCars();
    List<Car> getFreeCarsPerBranch(long idBranch);


    List<Car> getFreeCarsByKilometereRange(int range, int sector);

    CarModel getCarModelById(long id);

    long addCommand(ContentValues command);
    boolean closeCommand(ContentValues command);
    List isCommandClosedWithinTen();

    public boolean updateCarOnCloseCommand(ContentValues updateCar);
    public boolean updateCarOnOpenCommand(ContentValues updateCar);

    public List<Command> getCommandByClient(long idClient);
}
