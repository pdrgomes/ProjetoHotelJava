package com.fadergs.api.dao;


import com.fadergs.api.dao.DaoHospede;
import com.fadergs.api.dao.DaoMetodoPagamento;
import com.fadergs.api.dao.DaoQuarto;
import com.fadergs.api.dao.DaoRegistrarQuarto;
import com.fadergs.api.dao.DaoServico;
import com.fadergs.api.model.*;

public class DaoSupplier {
	
	public static DaoBase<Hospede> getDaoHospede() {
		return new DaoHospede();
	}
	public static DaoBase<Quarto> getDaoQuarto() {
		return new DaoQuarto();
	}
	public static DaoBase<MetodoPagamento> getDaoMetodoPagamento() {
		return new DaoMetodoPagamento();
	}
	public static DaoBase<TipoQuarto> getDaoTipoQuarto() {
		return new DaoTipoQuarto();
	}
	public static DaoBase<Servicos> getDaoServico() {
		return new DaoServico();
	}
	public static DaoRegistrarQuarto getDaoRegistrarQuarto() {
		return new DaoRegistrarQuarto();
	}
	public static DaoBase<ComprarServico> getDaoComprarServico() {
		return new DaoComprarServico();
	}
	
}
