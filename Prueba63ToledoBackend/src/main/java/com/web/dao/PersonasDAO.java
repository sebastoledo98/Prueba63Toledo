package com.web.dao;

import jakarta.ejb.Stateless;

import java.util.List;
import com.web.modelos.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class PersonasDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Persona cliente){
		em.persist(cliente);
	}

	public void update(Persona cliente){
		em.merge(cliente);
	}

	public void remove(int codigo){
		Persona cliente = em.find(Persona.class, codigo);
		em.remove(cliente);
	}

	public Persona read(int codigo){
		Persona cliente = em.find(Persona.class, codigo);
		return cliente;
	}

    public Persona getPersonaPorCedula(String cedula) {
		String sentencia = "SELECT p FROM Persona p WHERE p.dni = :cedula";
		Query q = em.createQuery(sentencia, Persona.class);
        q.setParameter("cedula",cedula);
        List<Persona> clientes = q.getResultList();
        if(clientes.size() > 0)
            return clientes.get(0);
        return null;
    }

	public List<Persona> getAll(){
		String sentencia = "SELECT p FROM Persona p";
		Query q = em.createQuery(sentencia, Persona.class);
		return q.getResultList();
	}

}
