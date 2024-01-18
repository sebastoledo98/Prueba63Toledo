package com.web.services;

import com.web.business.ErrorMessage;
import com.web.business.GestionPersonas;
import com.web.modelos.Persona;

import java.util.List;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("personas")
public class PersonaService {
    
	@Inject
    private GestionPersonas gPersonas;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Persona persona){
    	System.out.println(persona);
        try{
            gPersonas.guardarCliente(persona);
            ErrorMessage error = new ErrorMessage(1, "OK");
            //return Response.ok(persona).build();
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
    public Response actualizar(Persona persona){
        try{
            gPersonas.guardarCliente(persona);
            return Response.ok(persona).build();
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
            gPersonas.borrarPersona(codigo);
            return "OK";
        }catch (Exception e) {
            return "Error";
         }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("dni")String cedula, @QueryParam("nombre") String nombre){
        try{
        	System.out.println("cedula: " + cedula + ", nombre = " + nombre);
            Persona cli = gPersonas.getPersonaPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
        	ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
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
            Persona cli = gPersonas.getPersonaPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
        	ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            		.entity(error)
            		.build();
         }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getPersona(){
    	System.out.println("Listando");
    	List<Persona> personas = gPersonas.getPersona();
    	if(personas.size() > 0)
    		return Response.ok(personas).build();
    	
    	ErrorMessage error = new ErrorMessage(6, "No se registran personas");
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        		.entity(error)
        		.build();
    }

}
