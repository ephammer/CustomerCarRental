package com.ephraimhammer.jct.customercarrental.model.entities;

import java.util.Date;

public class Command {
    long commandId;
    long clientId;
    COMMAND_STATE commandState;
    long carId;
    Date startRentingDate;
    Date endRentingDate;
    int  startNumberKilometre;
    int endNumberKilometre;
    FUEL_STATE fuel_state;
    int amountOfLiterFilled;
    float price;


    public long getCommandId() {
        return commandId;
    }

    public void setCommandId(long commandId) {
        this.commandId = commandId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public COMMAND_STATE getCommandState() {
        return commandState;
    }

    public void setCommandState(COMMAND_STATE commandState) {
        this.commandState = commandState;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public Date getStartRentingDate() {
        return startRentingDate;
    }

    public void setStartRentingDate(Date startRentingDate) {
        this.startRentingDate = startRentingDate;
    }

    public Date getEndRentingDate() {
        return endRentingDate;
    }

    public void setEndRentingDate(Date endRentingDate) {
        this.endRentingDate = endRentingDate;
    }

    public int getStartNumberKilometre() {
        return startNumberKilometre;
    }

    public void setStartNumberKilometre(int startNumberKilometre) {
        this.startNumberKilometre = startNumberKilometre;
    }

    public int getEndNumberKilometre() {
        return endNumberKilometre;
    }

    public void setEndNumberKilometre(int endNumberKilometre) {
        this.endNumberKilometre = endNumberKilometre;
    }

    public FUEL_STATE getFuel_state() {
        return fuel_state;
    }

    public void setFuel_state(FUEL_STATE fuel_state) {
        this.fuel_state = fuel_state;
    }

    public int getAmountOfLiterFilled() {
        return amountOfLiterFilled;
    }

    public void setAmountOfLiterFilled(int amountOfLiterFilled) {
        this.amountOfLiterFilled = amountOfLiterFilled;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
