package com.fadergs.api.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fadergs.api.dao.DaoMetodoPagamento;
import com.fadergs.api.dao.DaoSupplier;
import com.fadergs.api.model.MetodoPagamento;
import com.fadergs.api.model.Status;
import com.fadergs.api.resources.util.ResponseBuilderControl;

@Path("/metodoPagamento")
@Produces("application/json")
public class MetodoPagamentoResource {
	
	@GET
	@Path("/buscar")
	public Response getMetodoPagamento(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		MetodoPagamento tQuarto = new DaoMetodoPagamento().findById(id);
		
		if (tQuarto != null) {  
			resBuilder =
					Response.ok().entity(tQuarto);
		} else {
			resBuilder =
					Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();	
	}
	
	@GET
	@Path("/buscarTodos")
	public Response getMetodosPagamentos(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		List<MetodoPagamento> mPagamento = DaoSupplier.getDaoMetodoPagamento().findAll();

		if (mPagamento != null) {
			resBuilder = Response.ok().entity(mPagamento);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@POST
	@Path("/inserir")
	public Response inserir(MetodoPagamento mPagamento) {
		Response.ResponseBuilder resBuilder = null;
	
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoMetodoPagamento().insert(mPagamento));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			e.printStackTrace();
		}
		
		

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@DELETE
	@Path("/delete")
	public Response delete(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		
		try {
			new DaoMetodoPagamento().delete(id);
			resBuilder = Response.ok().entity(new Status(true, Status.DELETADO));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@PUT
	@Path("/update")
	public Response update(MetodoPagamento mPagamento) {
		Response.ResponseBuilder resBuilder = null;
		
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoMetodoPagamento().update(mPagamento));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			e.printStackTrace();
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
}
