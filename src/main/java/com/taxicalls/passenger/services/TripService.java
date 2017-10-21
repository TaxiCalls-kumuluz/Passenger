/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.passenger.services;

import com.taxicalls.passenger.model.Driver;
import com.taxicalls.passenger.model.Trip;
import com.taxicalls.utils.ServiceRegistry;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author romulo
 */
@ApplicationScoped
public class TripService {

    @Inject
    private ServiceRegistry serviceRegistry;

    @Inject
    public TripService() {
    }

    public Collection<Driver> getAvailableDrivers(Trip trip) {
        Collection<Driver> drivers = ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path("drivers").path("available")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(trip, MediaType.APPLICATION_JSON), new GenericType<Collection<Driver>>() {
                });
        return drivers;
    }
}
