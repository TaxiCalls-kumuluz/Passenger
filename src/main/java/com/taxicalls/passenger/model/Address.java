/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.passenger.model;

/**
 *
 * @author romulo
 */
public class Address {

    private final Coordinate coordinate;

    public Address(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Address() {
        this.coordinate = null;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

}
