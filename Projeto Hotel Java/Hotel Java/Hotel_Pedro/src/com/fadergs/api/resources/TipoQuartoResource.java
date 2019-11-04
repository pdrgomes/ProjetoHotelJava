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

import com.fadergs.api.dao.DaoSupplier;
import com.fadergs.api.dao.DaoTipoQuarto;
import com.fadergs.api.model.Status;
import com.fadergs.api.model.TipoQuarto;
import com.fadergs.api.resources.util.ResponseBuilderControl;

@Path("/tipoQuarto")
@Produces("application/json")
public class TipoQuartoResource {
	
	@GET
	@Path("/buscar")
	public Response getTipoQuarto(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		TipoQuarto tQuarto = new DaoTipoQuarto().findById(id);
		
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
	public Response getTipoQuartos(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		List<TipoQuarto> tQuarto = DaoSupplier.getDaoTipoQuarto().findAll();

		if (tQuarto != null) {
			resBuilder = Response.ok().entity(tQuarto);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@POST
	@Path("/inserir")
	public Response inserir(TipoQuarto tQuarto) {
		Response.ResponseBuilder resBuilder = null;
	
		try {
			DaoSupplier.getDaoTipoQuarto().insert(tQuarto);
			resBuilder = Response.ok().entity(tQuarto);
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
			new DaoTipoQuarto().delete(id);
			resBuilder = Response.ok().entity(new Status(true, Status.DELETADO));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	@PUT
	@Path("/update")
	public Response update(TipoQuarto tQuarto) {
		Response.ResponseBuilder resBuilder = null;
		
		try {
			DaoSupplier.getDaoTipoQuarto().update(tQuarto);
			resBuilder = Response.ok().entity(tQuarto);
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			e.printStackTrace();
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
}
