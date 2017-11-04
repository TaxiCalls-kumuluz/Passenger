/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.passenger.resources;

import com.taxicalls.passenger.model.Coordinate;

/**
 *
 * @author romulo
 */
public class AvailableDriversRequest {

    private Coordinate coordinate;
    private int ratio;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getRatio() {
        return ratio;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

}
