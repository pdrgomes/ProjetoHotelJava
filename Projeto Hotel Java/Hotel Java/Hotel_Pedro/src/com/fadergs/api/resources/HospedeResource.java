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

import com.fadergs.api.dao.DaoHospede;
import com.fadergs.api.dao.DaoSupplier;
import com.fadergs.api.model.Hospede;
import com.fadergs.api.model.Status;
import com.fadergs.api.resources.util.ResponseBuilderControl;

@Path("/hospede")
@Produces("application/json")
public class HospedeResource {

	@GET
	@Path("/buscar")
	public Response getHospede(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Hospede hosp = new DaoHospede().findById(id);

		if (hosp != null) {
			resBuilder = Response.ok().entity(hosp);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@GET
	@Path("/buscarTodos")
	public Response getHospedes(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		List<Hospede> hospedes = DaoSupplier.getDaoHospede().findAll();

		resBuilder = Response.ok().entity(hospedes);
		

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	
	
	@POST
	@Path("/inserir")
	public Response inserir(Hospede hosp) {
		Response.ResponseBuilder resBuilder = null;
	
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoHospede().insert(hosp));
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
			new DaoHospede().delete(id);
			resBuilder = Response.ok().entity(new Status(true, Status.DELETADO));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@PUT
	@Path("/update")
	public Response update(Hospede hosp) {
		Response.ResponseBuilder resBuilder = null;
	
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoHospede().update(hosp));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			e.printStackTrace();
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
		
	}
}
