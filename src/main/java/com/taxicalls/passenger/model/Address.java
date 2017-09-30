/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.passenger.model;

import java.io.Serializable;

/**
 *
 * @author romulo
 */
public class Address implements Serializable {

    private final Coordinate coordinate;

    public Address(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}
