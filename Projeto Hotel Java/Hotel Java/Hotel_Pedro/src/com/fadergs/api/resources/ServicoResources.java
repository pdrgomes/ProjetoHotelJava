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

import com.fadergs.api.dao.DaoServico;
import com.fadergs.api.dao.DaoSupplier;
import com.fadergs.api.model.Servicos;
import com.fadergs.api.model.Status;
import com.fadergs.api.resources.util.ResponseBuilderControl;

@Path("/servico")
@Produces("application/json")
public class ServicoResources {
	
	@GET
	@Path("/buscar")
	public Response getHospede(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Servicos serv = new DaoServico().findById(id);
		
		if (serv != null) {  
			resBuilder =
					Response.ok().entity(serv);
		} else {
			resBuilder =
					Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();	
	}
	
	@GET
	@Path("/buscarTodos")
	public Response getQuartos(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		List<Servicos> servicos = DaoSupplier.getDaoServico().findAll();

		if (servicos != null) {
			resBuilder = Response.ok().entity(servicos);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@POST
	@Path("/inserir")
	public Response inserir(Servicos serv) {
		Response.ResponseBuilder resBuilder = null;
	
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoServico().insert(serv));
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
			new DaoServico().delete(id);
			resBuilder = Response.ok().entity(new Status(true, Status.DELETADO));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	
	@PUT
	@Path("/update")
	public Response update(Servicos serv) {
		Response.ResponseBuilder resBuilder = null;
		
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoServico().update(serv));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			e.printStackTrace();
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
}
