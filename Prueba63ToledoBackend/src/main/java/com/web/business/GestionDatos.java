package com.web.business;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

import com.web.dao.*;

import com.web.modelos.*;

@Singleton
@Startup
public class GestionDatos {
	
	@Inject
	private PersonasDAO personasDAO;
	@Inject
	private DeudasDAO deudasDAO;

	@PostConstruct
	public void init(){
		System.out.println("iniciando");
		//Persona 1
		Persona persona = new Persona();
		persona.setId(1);
		persona.setDni("0123456789");
		persona.setDireccion("Av. Don Bosco");
		persona.setNombre("Pepito Perez");

                Deudas deuda = new Deudas();
                deuda.setId(1);
                deuda.setMes("Febrero");
                deuda.setValor(200.15);
                persona.addDeuda(deuda);

		personasDAO.insert(persona);
		//Persona 2
		persona = new Persona();
		persona.setId(2);
		persona.setDni("0987654321");
		persona.setDireccion("El Vado");
		persona.setNombre("Diego Gomez");

                deuda = new Deudas();
                deuda.setId(2);
                deuda.setMes("Marzo");
                deuda.setValor(350.23);
                persona.addDeuda(deuda);

                deuda = new Deudas();
                deuda.setId(3);
                deuda.setMes("Abril");
                deuda.setValor(157.24);
                persona.addDeuda(deuda);

		personasDAO.insert(persona);
                //Persona 3
		persona = new Persona();
		persona.setId(3);
		persona.setDni("0147258369");
		persona.setDireccion("Americas");
		persona.setNombre("Juan Gonzalez");

                deuda = new Deudas();
                deuda.setId(4);
                deuda.setMes("Mayo");
                deuda.setValor(296.37);
                persona.addDeuda(deuda);

                deuda = new Deudas();
                deuda.setId(5);
                deuda.setMes("Julio");
                deuda.setValor(63.19);
                persona.addDeuda(deuda);

		personasDAO.insert(persona);
		//Listar Personas
		List<Persona> lista = personasDAO.getAll();
		for(Persona c : lista){
			System.out.print(c.getId() + "\n");
			System.out.print(c.getDni() + "\n");
			System.out.print(c.getDireccion() + "\n");
			System.out.print(c.getNombre() + "\n");
		}

		System.out.println("--------------Personas--------------");
		List<Persona> personas = personasDAO.getAll();
		for(Persona p: personas){
			System.out.println(p);
		}
	}

}
