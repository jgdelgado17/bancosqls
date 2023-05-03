package org.cuentas.controller;

import java.util.List;

import org.cuentas.entity.Cuenta;
import org.cuentas.exceptions.Message;
import org.cuentas.service.CuentaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cuentas")
@Produces(MediaType.APPLICATION_JSON)
public class CuentaController {

    @Inject
    CuentaService service;

    @GET
    public Response getAllCuentas() {
        try {
            List<Cuenta> cuentas = service.findAll();
            if (cuentas.isEmpty())
                return Response.ok(cuentas).status(Response.Status.NO_CONTENT).build();
            return Response.ok(cuentas).build();            
        } catch (Exception e) {
            String message = e.getCause().getCause().getCause().getMessage();
            Message messageResponse = new Message();
            messageResponse.buildMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getCuentaById(@PathParam("id") Long id) {
        try {
            Cuenta cuenta = service.findById(id);
            if (cuenta == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(cuenta).build();

        } catch (Exception e) {
            String message = e.getCause().getCause().getCause().getMessage();
            Message messageResponse = new Message();
            messageResponse.buildMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageResponse).build();
        }
    }

    @POST
    public Response postCuenta(Cuenta cuenta) {
        try {
            Cuenta createdCuenta = service.save(cuenta);
            return Response.ok(createdCuenta).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            String message = e.getCause().getMessage();
            Message messageResponse = new Message();
            messageResponse.buildMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageResponse).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response putCuenta(@PathParam("id") Long id, Cuenta cuenta) {
        try {
            Cuenta c = service.findById(id);
            if (c != null) {
                Cuenta updatedCuenta = service.update(id, cuenta);
                return Response.ok(updatedCuenta).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            String message = e.getCause().getCause().getCause().getMessage();
            Message messageResponse = new Message();
            messageResponse.buildMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageResponse).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCuenta(@PathParam("id") Long id) {
        try {
            Cuenta cuenta = service.findById(id);
            if (cuenta != null) {
                service.deleteById(id);
                return Response.noContent().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            String message = e.getCause().getCause().getCause().getMessage();
            Message messageResponse = new Message();
            messageResponse.buildMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageResponse).build();
        }
    }
}
