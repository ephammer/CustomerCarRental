package com.ephraimhammer.jct.customercarrental.model.entities;

public class CarModel {

    static long uniqueId = 2001 ;

    long modelId;
    String modelCompanyName;
    String modelName;
    int modelMotorVolume;
    boolean automatic;
    PASSENGERS passengers;
    DOOR door;
    static LUGGAGE luggageCompartment;
    CLASSE classe;
    int priceDay;
    boolean airC ;


    public CLASSE getClasse() {
        return classe;
    }

    public void setClasse(CLASSE classe) {
        this.classe = classe;
    }

    public int getPriceDay() {
        return priceDay;
    }

    public void setPriceDay() {

        switch (classe)
        {
            case ECONOMY: priceDay = 25;
            break;
            case COMPACT: priceDay = 35;
            break;
            case STANDARD: priceDay = 47;
            break;
            case BREAK: priceDay = 53;
            break;
            case INTERMEDIATE: priceDay  = 67;
            break;
            case SUV: priceDay = 85;
            break;
            case VAN: priceDay = 80;
            break;
            case LUXE: priceDay = 120;
            default:priceDay = 0 ;
        }
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {

        if(modelId > 0)
            this.modelId = modelId;
        else
        {
            this.modelId = uniqueId;
            uniqueId++;
        }
    }

    public String getModelCompanyName() {
        return modelCompanyName;
    }

    public void setModelCompanyName(String modelCompanyName) {
        this.modelCompanyName = modelCompanyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getModelMotorVolume() {
        return modelMotorVolume;
    }

    public void setModelMotorVolume() {

        switch (classe) {
            case ECONOMY:
                modelMotorVolume = 36;
                break;
            case COMPACT:
                modelMotorVolume = 45;
                break;
            case STANDARD:
                modelMotorVolume = 47;
                break;
            case BREAK:
                modelMotorVolume = 53;
                break;
            case INTERMEDIATE:
                modelMotorVolume = 57;
                break;
            case SUV:
                modelMotorVolume = 85;
                break;
            case VAN:
                modelMotorVolume = 90;
                break;
            case LUXE:
                modelMotorVolume = 89;
                break;
            default:
                modelMotorVolume = 0;

        }
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public PASSENGERS getPassengers() {
        return passengers;
    }

    public void setPassengers(PASSENGERS passengers) {
        this.passengers = passengers;
    }

    public DOOR getDoor() {
        return door;
    }

    public void setDoor() {
        switch (classe)
        {
            case ECONOMY: door = DOOR.TWO;
                break;
            case COMPACT: door = DOOR.THREE;
                break;
            case STANDARD: door = DOOR.THREE;
                break;
            case BREAK: door = DOOR.THREE;
                break;
            case INTERMEDIATE: door = DOOR.FOUR;
                break;
            case SUV: door = DOOR.FIVE;
                break;
            case VAN: door = DOOR.FIVE;
                break;
            case LUXE: door = DOOR.THREE;
            default:door = DOOR.THREE;
        }
    }

    public static LUGGAGE getLuggageCompartment() {
        return luggageCompartment;
    }

    public void setLuggageCompartment(LUGGAGE luggageCompartment) {
        this.luggageCompartment = luggageCompartment;
    }

    public boolean isAirC() {
        return airC;
    }

    public void setAirC(boolean airC) {
        this.airC = airC;
    }


}
