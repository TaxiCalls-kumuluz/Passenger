/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.protocol;

/**
 *
 * @author romulo
 */
public class Response {

    private final Status status;
    private final String message;
    private final Object entity;

    protected Response() {
        this.status = null;
        this.message = null;
        this.entity = null;
    }

    public Response(Status status) {
        this.status = status;
        this.message = null;
        this.entity = null;
    }

    public Response(Object entity) {
        this.status = Status.SUCCESSFUL;
        this.message = null;
        this.entity = entity;
    }

    public Response(Status status, String message) {
        this.status = status;
        this.message = message;
        this.entity = null;
    }

    public Response(Status status, Object entity) {
        this.status = status;
        this.message = null;
        this.entity = entity;
    }

    public Response(Status status, String message, Object entity) {
        this.status = status;
        this.message = message;
        this.entity = entity;
    }

    public static Response notFound() {
        return new Response(Status.NOT_FOUND);
    }

    public static Response successful(Object object) {
        return new Response(Status.SUCCESSFUL, object);
    }
    
    public static Response successful() {
        return new Response(Status.SUCCESSFUL);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getEntity() {
        return entity;
    }

}
