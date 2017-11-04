/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.passenger.model;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author romulo
 */
public class Trip implements Serializable {

    private Integer id;
    private Driver driver;
    private Address addressFrom;
    private Address addressTo;
    private Set<Passenger> passengers;

    protected Trip() {
    }

    public Trip(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public Address getAddressFrom() {
        return addressFrom;
    }

    public Address getAddressTo() {
        return addressTo;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

}
