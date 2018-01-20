package com.ephraimhammer.jct.customercarrental.model.entities;

import java.util.Random;

/**
 * Created by binyamin on 09/11/2017.
 */

public class Branch {

    static long uniqueId = 4001 ;
    String branchName;

    String branchCity;
    String branchStreet;
    int sector;
    int branchStreetNumber;
    int branchAmountParkingPlace;
    long branchId;

    public Branch() {
    }

    public Branch(String branchCity, String branchStreet, int sector, int branchStreetNumber, int branchAmountParkingPlace) {
        this.branchCity = branchCity;
        this.branchStreet = branchStreet;
        setSector(0);
        this.branchStreetNumber = branchStreetNumber;
        this.branchAmountParkingPlace = branchAmountParkingPlace;
        setBranchName("Take&go  - " + branchStreet + " -");
        setBranchId(0);
    }

    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String name) {
        this.branchName = name;
    }



    public int getSector() {
        return sector;
    }

    public void setSector(int sector)
    {

        int  sec = 0;

        if(sector == 0)
        {
            Random rand = new Random();

            sec = rand.nextInt(20) + 1;
        }
        this.sector = sec;
    }    


    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchStreet() {
        return branchStreet;
    }

    public void setBranchStreet(String branchStreet) {
        this.branchStreet = branchStreet;
    }

    public int getBranchStreetNumber() {
        return branchStreetNumber;
    }

    public void setBranchStreetNumber(int branchStreetNumber) {
        this.branchStreetNumber = branchStreetNumber;
    }

    public int getBranchAmountParkingPlace() {
        return branchAmountParkingPlace;
    }

    public void setBranchAmountParkingPlace(int branchAmountParkingPlace) {
        this.branchAmountParkingPlace = branchAmountParkingPlace;
    }

    public  long getBranchId() {
        return branchId;
    }

    public  void setBranchId(long branchId) {

        if(branchId >0)
            this.branchId = branchId;

        else
        {
            this.branchId = uniqueId;
            uniqueId++;
        }

    }
}
