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

    private Long id;
    private Address addressFrom;
    private Address addressTo;
    private Driver driver;
    private Passenger author;
    private Set<Passenger> passengers;
    private Progress progress;

    protected Trip() {
    }

    public Trip(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Address getAddressFrom() {
        return addressFrom;
    }

    public Address getAddressTo() {
        return addressTo;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getAuthor() {
        return author;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

}
