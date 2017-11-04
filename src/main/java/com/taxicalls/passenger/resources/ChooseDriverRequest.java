/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.passenger.resources;

import com.taxicalls.passenger.model.Driver;
import com.taxicalls.passenger.model.Passenger;

/**
 *
 * @author romulo
 */
public class ChooseDriverRequest {

    private Passenger passenger;
    private Driver driver;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

}
