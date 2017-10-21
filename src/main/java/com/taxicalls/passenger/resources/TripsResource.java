package com.taxicalls.passenger.resources;

import com.taxicalls.passenger.model.Driver;
import com.taxicalls.passenger.model.Trip;
import com.taxicalls.passenger.services.NotificationService;
import com.taxicalls.passenger.services.TripService;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TripsResource {

    @Inject
    private TripService tripService;
    
    @Inject
    private NotificationService notificationService;
    
    private static final Logger LOGGER = Logger.getLogger(TripsResource.class.getName());

    @POST
    @Path("/drivers/available")
    public Response getAvailableDrivers(Trip trip) {
        LOGGER.log(Level.INFO, "getAvailableDrivers() invoked");
        Collection<Driver> availableDrivers = tripService.getAvailableDrivers(trip);
        LOGGER.log(Level.INFO, "getAvailableDrivers() found {0}", availableDrivers.size());
        return Response.ok(availableDrivers).build();
    }
    
    @POST
    @Path("/drivers/choose")
    public Response chooseDriver(Driver driver) {
        LOGGER.log(Level.INFO, "chooseDriver() invoked");
        notificationService.chooseDriver(driver);
        return Response.ok().build();
    }
}
