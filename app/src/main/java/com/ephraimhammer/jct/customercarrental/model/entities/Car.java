package com.ephraimhammer.jct.customercarrental.model.entities;

/**
 * Created by binyamin on 10/11/2017.
 */

public class Car {
    static long uniqueId = 3001 ;

    long branchIdCarParked;
    long typeModelID;
    int kilometre;
    boolean isFree;
    long carId;

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public long getBranchIdCarParked() {
        return branchIdCarParked;
    }

    public void setBranchIdCarParked(long branchIdCarParked) {
        this.branchIdCarParked = branchIdCarParked;
    }

    public long getTypeModelID() {
        return typeModelID;
    }

    public void setTypeModelID(long typeModelID) {
        this.typeModelID = typeModelID;
    }

    public int getKilometre() {
        return kilometre;
    }

    public void setKilometre(int kilometre) {
        this.kilometre = kilometre;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {

        if(carId > 0)
            this.carId = carId;
        else
        {
            this.carId = uniqueId;
            uniqueId++;
        }
    }
}
