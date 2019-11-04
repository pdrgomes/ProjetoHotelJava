package com.fadergs.api.model;

public class ComprarServico {

		private int id;
		private Compra Compra;
		private Servicos Servico;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Compra getCompra() {
			return Compra;
		}
		public void setCompra(Compra compra) {
			this.Compra = compra;
		}
		public Servicos getServico() {
			return Servico;
		}
		public void setServico(Servicos servico) {
			this.Servico = servico;
		}
		@Override
		public String toString() {
			return "Serviço Registrado [id=" + id + ", idCompra=" + Compra + ", idServico=" + Servico + "]";
		}

		
}
