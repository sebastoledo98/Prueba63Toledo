package com.web.modelos;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
public class Persona {

	@Id
	private int id;
	private String dni;
	private String nombre;
	private String direccion;
        @OneToMany
        @JoinColumn(name="id_deuda")
        private List<Deudas> deudas;

	public int getId() {
            return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

        public void addDeuda(Deudas deuda){
                if(deudas == null)
                    deudas = new ArrayList<Deudas>();
                deudas.add(deuda);
        }

        public List<Deudas> getDeudas(){
            return this.deudas;
        }

}
