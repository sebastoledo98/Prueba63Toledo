package com.web.dao;

import java.util.List;
import com.web.modelos.Deudas;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class DeudasDAO {
	@PersistenceContext
	private EntityManager em;

	public void insert(Deudas deuda){
		em.persist(deuda);
	}

	public void update(Deudas deuda){
		em.merge(deuda);
	}

	public void remove(int codigo){
		Deudas deuda = em.find(Deudas.class, codigo);
		em.remove(deuda);
	}

	public Deudas read(int codigo){
		Deudas deuda = em.find(Deudas.class, codigo);
		return deuda;
	}

    public Deudas getDeudaPorCedula(String cedula) {
		String sentencia = "SELECT d FROM Deudas d WHERE d.dni = :cedula";
		Query q = em.createQuery(sentencia, Deudas.class);
        q.setParameter("cedula",cedula);
        List<Deudas> deudas = q.getResultList();
        if(deudas.size() > 0)
            return deudas.get(0);
        return null;
    }

	public List<Deudas> getAll(){
		String sentencia = "SELECT p FROM Deudas p";
		Query q = em.createQuery(sentencia, Deudas.class);
		return q.getResultList();
	}


}
