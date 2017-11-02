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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AuthenticateResource {

    private final EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(AuthenticateResource.class.getName());

    public AuthenticateResource() {
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
    public Response authenticatePassenger(Passenger passenger) {
        LOGGER.log(Level.INFO, "authenticatePassenger() invoked");
        List<Passenger> passengers = em.createNamedQuery("Passenger.findAll", Passenger.class).getResultList();
        for (Passenger stored : passengers) {
            if (stored.getEmail().equals(passenger.getEmail()) && stored.getPassword().equals(passenger.getPassword())) {
                return Response.successful(stored);
            }
        }
        return Response.notFound();
    }

}
