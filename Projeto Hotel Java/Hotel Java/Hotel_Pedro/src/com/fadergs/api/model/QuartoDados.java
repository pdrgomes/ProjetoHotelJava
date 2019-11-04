package com.fadergs.api.model;

public class QuartoDados {

	private int id;
	private String nomeHospede;
	private int numeroQuarto;
	private TipoQuarto tipoQuarto;
	private int diaria;
	private double valor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeHospede() {
		return nomeHospede;
	}
	public void setNomeHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}
	public int getNumeroQuarto() {
		return numeroQuarto;
	}
	public void setNumeroQuarto(int numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}
	public TipoQuarto getTipoQuarto() {
		return tipoQuarto;
	}
	public void setTipoQuarto(TipoQuarto tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}
	public int getDiaria() {
		return diaria;
	}
	public void setDiaria(int diaria) {
		this.diaria = diaria;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return  "Hospede=" + nomeHospede + ", Numero Quarto=" + numeroQuarto
				+ ", Tipo Quarto=" + tipoQuarto + ", Diária=" + diaria + ", Valor=" + valor + "]";
	}
	
	
}
