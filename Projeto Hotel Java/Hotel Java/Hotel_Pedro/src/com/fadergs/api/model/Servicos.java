package com.fadergs.api.model;

public class Servicos {
	private int id;
	private String nomeServico;
	private Double precoServico;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public Double getPrecoServico() {
		return precoServico;
	}
	public void setPrecoServico(Double precoServico) {
		this.precoServico = precoServico;
	}
	@Override
	public String toString() {
		return "Servicos [id=" + id + ", nomeServico=" + nomeServico + ", precoServico=" + precoServico + "]";
	}

	
}
