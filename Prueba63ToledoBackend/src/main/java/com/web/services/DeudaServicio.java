package com.web.services;

import com.web.business.ErrorMessage;
import com.web.business.GestionDeudas;
import com.web.modelos.Deudas;

import java.util.List;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("deudas")
public class DeudaServicio {
    @Inject
    private GestionDeudas gDeudas;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Deudas deuda){
    	System.out.println(deuda);
        try{
            gDeudas.guardarDeuda(deuda);
            ErrorMessage error = new ErrorMessage(1, "OK");
            //return Response.ok(deuda).build();
            return Response.status(Response.Status.CREATED)
                .entity(error)
                .build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            		.entity(error)
            		.build();
         }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Deudas deuda){
        try{
            gDeudas.guardarDeuda(deuda);
            return Response.ok(deuda).build();
        }catch (Exception e) {
        	ErrorMessage error = new ErrorMessage(99, e.getMessage());
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            		.entity(error)
            		.build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int codigo){
        try{
            gDeudas.borrarDeuda(codigo);
            return "OK";
        }catch (Exception e) {
            return "Error";
         }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("dni")String cedula){
        try{
        	System.out.println("cedula: " + cedula);
            Deudas deu = gDeudas.getDeudaPorCedula(cedula);
            return Response.ok(deu).build();
        }catch (Exception e) {
        	ErrorMessage error = new ErrorMessage(4, "Deuda no existe");
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            		.entity(error)
            		.build();
         }
    }
    
    @GET
    @Path("{dni}/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer2(@PathParam("dni")String cedula,@PathParam("nombre") String nombre) {
    	try{
        	System.out.println("cedula: " + cedula + ", nombre = " + nombre);
            Deudas deu = gDeudas.getDeudaPorCedula(cedula);
            return Response.ok(deu).build();
        }catch (Exception e) {
        	ErrorMessage error = new ErrorMessage(4, "Deuda no existe");
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            		.entity(error)
            		.build();
         }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getDeuda(){
    	System.out.println("Listando");
    	List<Deudas> deudas = gDeudas.getDeudas();
    	if(deudas.size() > 0)
    		return Response.ok(deudas).build();
    	
    	ErrorMessage error = new ErrorMessage(6, "No se registran deudas");
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        		.entity(error)
        		.build();
    }


}
