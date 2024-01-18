package com.web.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Deudas {
	
	@Id
	private int id;
	private String mes;
	private double valor;
        	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
