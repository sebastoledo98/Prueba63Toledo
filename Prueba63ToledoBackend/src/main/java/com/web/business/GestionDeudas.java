package com.web.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Deudas;
import com.web.dao.DeudasDAO;

@Stateless
public class GestionDeudas {

    @Inject
    private DeudasDAO deudaDao;

    public void guardarDeuda(Deudas deuda){
    	System.out.println("deuda = " + deuda);
        Deudas deu = deudaDao.read(deuda.getId());
        System.out.println("deu = " + deu);
        if(deu == null)
        	deudaDao.insert(deuda);
        else
            deudaDao.update(deuda);
    }
    
    public void actualizarDeuda(Deudas deuda) throws Exception{
    	Deudas deu = deudaDao.read(deuda.getId());
    	if(deu != null) 
    		deudaDao.update(deuda);
		else
			throw new Exception("Deuda no existe");
    }

    public Deudas getDeudaPorCedula(String cedula) throws Exception{
        if(cedula.length() != 10)
            throw new Exception("Cedula incorrecta");
        return deudaDao.getDeudaPorCedula(cedula);
    }

    public void borrarDeuda(int id){
        deudaDao.remove(id);
    }

    public List<Deudas> getDeudas(){
        return deudaDao.getAll();
    }
}
