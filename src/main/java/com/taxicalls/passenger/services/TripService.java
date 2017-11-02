/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.passenger.services;

import com.taxicalls.passenger.model.Trip;
import com.taxicalls.protocol.Response;
import com.taxicalls.utils.ServiceRegistry;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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

    public Response getAvailableDrivers(Trip trip) {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path("drivers").path("available")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(trip, MediaType.APPLICATION_JSON), Response.class);
    }
}
