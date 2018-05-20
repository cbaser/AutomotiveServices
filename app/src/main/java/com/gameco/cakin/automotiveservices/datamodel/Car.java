package com.gameco.cakin.automotiveservices.datamodel;

import java.util.List;

/**
 * Created by cakin on 11/22/2017.
 */

public class Car {
   private String VIN;
   private String Average_Distance_Longterm;
   private String Battery_Level;
   private String Fuel_Consumption;
   private String Mileage;
   private String Next_Service_Distance;
   private String Remaining_Fuel;
   private String Average_Distance;
   private String ECO_Time;

    public Car(String VIN, String average_Distance_Longterm, String battery_Level, String fuel_Consumption, String mileage, String next_Service_Distance, String ECO_Time, String remaining_Fuel, String average_Distance) {
        this.VIN = VIN;
        this.Average_Distance_Longterm = average_Distance_Longterm;
        this.Battery_Level = battery_Level;
        this.Fuel_Consumption = fuel_Consumption;
        this.Mileage = mileage;
        this.Next_Service_Distance = next_Service_Distance;
        this.ECO_Time = ECO_Time;
        this.Remaining_Fuel = remaining_Fuel;
        this.Average_Distance = average_Distance;
    }
    public Car() {
    }


    public String getECO_Time() {
        return ECO_Time;
    }

    public void setECO_Time(String ECO_Time) {
        this.ECO_Time = ECO_Time;
    }
    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getAverage_Distance_Longterm() {
        return Average_Distance_Longterm;
    }

    public void setAverage_Distance_Longterm(String average_Distance_Longterm) {
        Average_Distance_Longterm = average_Distance_Longterm;
    }

    public String getBattery_Level() {
        return Battery_Level;
    }

    public void setBattery_Level(String battery_Level) {
        Battery_Level = battery_Level;
    }

    public String getFuel_Consumption() {
        return Fuel_Consumption;
    }

    public void setFuel_Consumption(String fuel_Consumption) {
        Fuel_Consumption = fuel_Consumption;
    }

    public String getMileage() {
        return Mileage;
    }

    public void setMileage(String mileage) {
        Mileage = mileage;
    }

    public String getNext_Service_Distance() {
        return Next_Service_Distance;
    }

    public void setNext_Service_Distance(String next_Service_Distance) {
        Next_Service_Distance = next_Service_Distance;
    }

    public String getRemaining_Fuel() {
        return Remaining_Fuel;
    }

    public void setRemaining_Fuel(String remaining_Fuel) {
        Remaining_Fuel = remaining_Fuel;
    }

    public String getAverage_Distance() {
        return Average_Distance;
    }

    public void setAverage_Distance(String average_Distance) {
        Average_Distance = average_Distance;
    }






}
//    private String carName;
//    private String VIN;
//    public String getVIN() {
//        return VIN;
//    }
//
//    public void setVIN(String VIN) {
//        this.VIN = VIN;
//    }
//
//    public List<Telematics> getTelematicsList() {
//        return telematicsList;
//    }
//
//    public void setTelematicsList(List<Telematics> telematicsList) {
//        this.telematicsList = telematicsList;
//    }
//
//    private List<Telematics> telematicsList;
//
//
//    public String getMileage() {
//        return mileage;
//    }
//
//    public void setMileage(String mileage) {
//        this.mileage = mileage;
//    }
//
//    private String mileage;
//
//    public String getCoverage_of_tank() {
//        return coverage_of_tank;
//    }
//
//    public void setCoverage_of_tank(String coverage_of_tank) {
//        this.coverage_of_tank = coverage_of_tank;
//    }
//
//    public String getAverage_distance_per_week() {
//        return average_distance_per_week;
//    }
//
//    public void setAverage_distance_per_week(String average_distance_per_week) {
//        this.average_distance_per_week = average_distance_per_week;
//    }
//
//    public String getAverage_distance_long_term() {
//        return average_distance_long_term;
//    }
//
//    public void setAverage_distance_long_term(String average_distance_long_term) {
//        this.average_distance_long_term = average_distance_long_term;
//    }
//
//    public String getDriving_style() {
//        return driving_style;
//    }
//
//    public void setDriving_style(String driving_style) {
//        this.driving_style = driving_style;
//    }
//
//    public String getActive_time_of_eco_plus() {
//        return active_time_of_eco_plus;
//    }
//
//    public void setActive_time_of_eco_plus(String active_time_of_eco_plus) {
//        this.active_time_of_eco_plus = active_time_of_eco_plus;
//    }
//
//    public String getActive_time_of_eco_mode() {
//        return active_time_of_eco_mode;
//    }
//
//    public void setActive_time_of_eco_mode(String active_time_of_eco_mode) {
//        this.active_time_of_eco_mode = active_time_of_eco_mode;
//    }
//
//    public String getFuel_consumption() {
//        return fuel_consumption;
//    }
//
//    public void setFuel_consumption(String fuel_consumption) {
//        this.fuel_consumption = fuel_consumption;
//    }
//
//    private String coverage_of_tank;
//    private String average_distance_per_week;
//    private String average_distance_long_term;
//    private String driving_style;
//    private String active_time_of_eco_plus;
//    private String active_time_of_eco_mode;
//    private String fuel_consumption;
//
//    public String getBatteryVoltage() {
//        return batteryVoltage;
//    }
//
//    public void setBatteryVoltage(String batteryVoltage) {
//        this.batteryVoltage = batteryVoltage;
//    }
//
//    private String batteryVoltage;
//
//    public String getRemaining_fuel() {
//        return remaining_fuel;
//    }
//
//    public void setRemaining_fuel(String remaining_fuel) {
//        this.remaining_fuel = remaining_fuel;
//    }
//
//    private String remaining_fuel;
//
//    public String getNextServiceDistance() {
//        return nextServiceDistance;
//    }
//
//    public void setNextServiceDistance(String nextServiceDistance) {
//        this.nextServiceDistance = nextServiceDistance;
//    }
//
//    private String nextServiceDistance;
//
//    public String getCarName() {
//        return carName;
//    }
//
//    public void setCarName(String carName) {
//        this.carName = carName;
//    }