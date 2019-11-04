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

import com.fadergs.api.dao.DaoQuarto;
import com.fadergs.api.dao.DaoSupplier;
import com.fadergs.api.model.Quarto;
import com.fadergs.api.model.Status;
import com.fadergs.api.resources.util.ResponseBuilderControl;

@Path("/quarto")
@Produces("application/json")
public class QuartoResource {
	
	@GET
	@Path("/buscar")
	public Response getQuarto(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Quarto quart = new DaoQuarto().findById(id);
		
		if (quart != null) {  
			resBuilder =
					Response.ok().entity(quart);
		} else {
			resBuilder =
					Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();	
	}
	
	@GET
	@Path("/buscarTodos")
	public Response getQuartos() {
		Response.ResponseBuilder resBuilder = null;
		List<Quarto> quartos = DaoSupplier.getDaoQuarto().findAll();

		if (quartos != null) {
			resBuilder = Response.ok().entity(quartos);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	@POST
	@Path("/inserir")
	public Response inserir(Quarto quarto) {
		Response.ResponseBuilder resBuilder = null;
	
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoQuarto().insert(quarto));
			
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@DELETE
	@Path("/delete")
	public Response delete(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		
		try {
			new DaoQuarto().delete(id);
			resBuilder = Response.ok().entity(new Status(true, Status.DELETADO));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@PUT
	@Path("/update")
	public Response update(Quarto quarto) {
		Response.ResponseBuilder resBuilder = null;
		
		try {
			
			resBuilder = Response.ok().entity(DaoSupplier.getDaoQuarto().update(quarto));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			e.printStackTrace();
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

}
