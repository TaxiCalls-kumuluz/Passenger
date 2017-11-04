package com.taxicalls.passenger.resources;

import com.taxicalls.passenger.model.Trip;
import com.taxicalls.passenger.services.NotificationService;
import com.taxicalls.passenger.services.TripService;
import com.taxicalls.protocol.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

    private final EntityManager em;

    public TripsResource() {
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<>();
        env.keySet().forEach((envName) -> {
            if (envName.contains("DATABASE_USER")) {
                configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
            } else if (envName.contains("DATABASE_PASS")) {
                configOverrides.put("javax.persistence.jdbc.password", env.get(envName));
            }
        });
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("passenger", configOverrides);
        this.em = emf.createEntityManager();
    }

    @POST
    public Response createTrip(Trip trip) {
        LOGGER.log(Level.INFO, "createTrip() invoked");
        return Response.successful(trip);
    }

    @POST
    @Path("/drivers/available")
    public Response getAvailableDrivers(Trip trip) {
        LOGGER.log(Level.INFO, "getAvailableDrivers() invoked");
        AvailableDriversRequest availableDriversRequest = new AvailableDriversRequest();
        availableDriversRequest.setCoordinate(trip.getAddressFrom().getCoordinate());
        availableDriversRequest.setRatio(5);
        return tripService.getAvailableDrivers(availableDriversRequest);
    }

    @POST
    @Path("/drivers/choose")
    public Response chooseDriver(ChooseDriverRequest chooseDriverRequest) {
        LOGGER.log(Level.INFO, "chooseDriver() invoked");
        return notificationService.chooseDriver(chooseDriverRequest);
    }
}
