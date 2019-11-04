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

import com.fadergs.api.dao.DaoRegistrarQuarto;
import com.fadergs.api.dao.DaoSupplier;
import com.fadergs.api.model.Compra;
import com.fadergs.api.model.Status;
import com.fadergs.api.resources.util.ResponseBuilderControl;

@Path("/registrar")
@Produces("application/json")
public class RegistrarResource {

	//INICIO DESIGNAR QUARTO
	
	@POST
	@Path("/registrarQuarto")
	public Response registrarQuarto(Compra compr) {
		Response.ResponseBuilder resBuilder = null;
	
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoRegistrarQuarto().insert(compr));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			e.printStackTrace();
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@GET
	@Path("/buscar")
	public Response getRegistro(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Compra comp = new DaoRegistrarQuarto().findById(id);

		if (comp != null) {
			resBuilder = Response.ok().entity(comp);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@GET
	@Path("/buscarTodos")
	public Response getRegistros(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		List<Compra> comp = DaoSupplier.getDaoRegistrarQuarto().findAll();

		resBuilder = Response.ok().entity(comp);
		

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@GET
	@Path("/filtrarComprasPorQuarto")
	public Response getRegistrosByIdQuarto(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		List<Compra> comp = DaoSupplier.getDaoRegistrarQuarto().findComprasByIdQuarto(id);
		resBuilder = Response.ok().entity(comp);

		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@DELETE
	@Path("/delete")
	public Response delete(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		
		try {
			new DaoRegistrarQuarto().delete(id);
			resBuilder = Response.ok().entity(new Status(true, Status.DELETADO));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@PUT
	@Path("/update")
	public Response update(Compra compr) {
		Response.ResponseBuilder resBuilder = null;
	
		try {
			resBuilder = Response.ok().entity(DaoSupplier.getDaoRegistrarQuarto().update(compr));
		} catch (Exception e) {
			resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			e.printStackTrace();
		}
		
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	//FIM DE DESIGNAR QUARTO
	
	//==================================================================
	
	
	
}
