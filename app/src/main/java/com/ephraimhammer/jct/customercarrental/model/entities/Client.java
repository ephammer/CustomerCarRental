package com.ephraimhammer.jct.customercarrental.model.entities;

import java.util.Random;

/**
 * Created by binyamin on 09/11/2017.
 */

public class Client {

    static long uniqueId = 1001 ;
    String firstName;
    String lastName;
    String phoneNumber;
    String mailAdress;
    String creditCardNumber;
    String password;
    int sector;
    long clientId;

    public Client(String firstName, String lastName, String phoneNumber, String mailAdress, String creditCardNumber, String password, int sector) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mailAdress = mailAdress;
        this.creditCardNumber = creditCardNumber;
        this.password = password;
        setSector(0);
        setClientId(0);
    }

    public Client() {
    }

    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {

        int  sec = 0;

        if(sector == 0)
        {
            Random rand = new Random();

            sec = rand.nextInt(20) + 1;
        }
        this.sector = sec;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getClientId() {
        return clientId;
    }

    // if id < 0 so need to set the id by the system.
    public void setClientId(long clientId) {
        if(clientId >=1)
        this.clientId = clientId;
        
        else
        {
            this.clientId = uniqueId;
            uniqueId++;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }




}
