package com.fadergs.api.model;

public class Compra {

	private int id;
	private Hospede hospede;
	private Quarto Quarto;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hosp) {
		this.hospede = hosp;
	}

	public Quarto getQuarto() {
		return Quarto;
	}

	public void setIdQuarto(Quarto quarto) {
		this.Quarto = quarto;
	}

	@Override
	public String toString() {
		return "RegistroQuarto [id=" + id + ", idHospede=" + hospede + ", idQuarto=" + Quarto + "]";
	}

	

	
}
