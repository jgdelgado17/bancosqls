package org.cuentas.controller;

import java.util.List;

import org.cuentas.entity.Movimiento;
import org.cuentas.exceptions.Message;
import org.cuentas.service.MovimientoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/movimientos")
public class MovimientoController {

    @Inject
    MovimientoService service;

    @GET
    public Response getAllMovimientos() {
        try {
            List<Movimiento> movimientos = service.findAll();
            if (movimientos.isEmpty())
                return Response.ok(movimientos).status(Response.Status.NO_CONTENT).build();
            return Response.ok(movimientos).build();
        } catch (Exception e) {
            String message = e.getCause().getCause().getCause().getMessage();
            Message messageResponse = new Message();
            messageResponse.buildMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageResponse).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getMovimientoById(@PathParam("id") Long id) {
        try {
            Movimiento movimiento = service.findById(id);
            if (movimiento == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(movimiento).build();

        } catch (Exception e) {
            String message = e.getCause().getCause().getCause().getMessage();
            Message messageResponse = new Message();
            messageResponse.buildMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageResponse).build();
        }
    }

    @POST
    public Response postMovimiento(Movimiento movimiento) {
        try {
            Movimiento createdMovimiento = service.save(movimiento);
            return Response.ok(createdMovimiento).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            String message;

            if (e.getCause() != null)
                message = e.getCause().getMessage();
            else
                message = e.getMessage();

            Message messageResponse = new Message();
            messageResponse.buildMessage(message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageResponse).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response putMovimiento(@PathParam("id") Long id, Movimiento movimiento) {
        try {
            Movimiento c = service.findById(id);
            if (c != null) {
                Movimiento updatedMovimiento = service.update(id, movimiento);
                return Response.ok(updatedMovimiento).build();
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
    public Response deleteMovimiento(@PathParam("id") Long id) {
        try {
            Movimiento movimiento = service.findById(id);
            if (movimiento != null) {
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
