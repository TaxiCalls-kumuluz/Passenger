package com.taxicalls.passenger.resource;

import com.taxicalls.passenger.model.Passenger;
import com.taxicalls.passenger.model.Route;
import com.taxicalls.utils.ServiceRegistry;
import java.util.HashMap;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/passenger")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PassengerResource {

    private final EntityManager em;
    @Inject
    private ServiceRegistry serviceRegistry;

    public PassengerResource() {
        EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("passenger");
        this.em = createEntityManagerFactory.createEntityManager(new HashMap());
    }

    @GET
    public Response getPassenger() {
        List<Passenger> passengers = em.createNamedQuery("Passenger.findAll", Passenger.class).getResultList();
        return Response.ok(passengers).build();
    }

    @GET
    @Path("/{id}")
    public Response getPassenger(@PathParam("id") Integer id) {
        Passenger passenger = em.find(Passenger.class, id);
        if (passenger == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(passenger).build();
    }

    @POST
    @Path("/trip")
    public Response getAvailableRoutes(Route route) {
        List<Route> routes = ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("RoutesService"))
                .path("routes").path("available")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(route, MediaType.APPLICATION_JSON), new GenericType<List<Route>>() {
                });
        return Response.ok(routes).build();
    }

    @GET
    @Path("/trips")
    public Response getRoutes() {
        List<Route> routes = ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("RoutesService"))
                .path("routes")
                .request().get(new GenericType<List<Route>>() {
                });
        return Response.ok(routes).build();
    }

    @POST
    public Response createPassenger(Passenger passenger) {
        em.getTransaction().begin();
        em.persist(passenger);
        em.getTransaction().commit();
        return Response.status(Response.Status.CREATED).entity(passenger).build();
    }
}
