package com.taxicalls.passenger.resources;

import com.taxicalls.passenger.model.Passenger;
import com.taxicalls.protocol.Response;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/passengers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PassengersResource {

    private final EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(PassengersResource.class.getName());

    public PassengersResource() {
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
    public Response createPassenger(Passenger passenger) {
        em.getTransaction().begin();
        em.persist(passenger);
        em.getTransaction().commit();
        return Response.successful(passenger);
    }

    @GET
    public Response getPassengers() {
        LOGGER.log(Level.INFO, "getPassengers() invoked");
        List<Passenger> passengers = em.createNamedQuery("Passenger.findAll", Passenger.class).getResultList();
        LOGGER.log(Level.INFO, "getPassengers() found {0}", passengers.size());
        return Response.successful(passengers);
    }

    @GET
    @Path("/{id}")
    public Response getPassenger(@PathParam("id") Long id) {
        Passenger passenger = em.find(Passenger.class, id);
        if (passenger == null) {
            return Response.notFound();
        }
        return Response.successful(passenger);
    }
}
