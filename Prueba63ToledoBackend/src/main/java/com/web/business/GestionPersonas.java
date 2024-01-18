package com.web.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Persona;
import com.web.dao.PersonasDAO;

@Stateless
public class GestionPersonas {

    @Inject
    private PersonasDAO personaDao;

    public void guardarCliente(Persona persona){
    	System.out.println("persona = " + persona);
        Persona per = personaDao.read(persona.getId());
        System.out.println("per = " + per);
        if(per == null)
        	personaDao.insert(persona);
        else
            personaDao.update(persona);
    }
    
    public void actualizarPersona(Persona persona) throws Exception{
    	Persona per = personaDao.read(persona.getId());
    	if(per != null) 
    		personaDao.update(persona);
		else
			throw new Exception("Persona no existe");
    }

    public Persona getPersonaPorCedula(String cedula) throws Exception{
        if(cedula.length() != 10)
            throw new Exception("Cedula incorrecta");
        return personaDao.getPersonaPorCedula(cedula);
    }

    public void borrarPersona(int id){
        personaDao.remove(id);
    }

    public List<Persona> getPersona(){
        return personaDao.getAll();
    }
}
