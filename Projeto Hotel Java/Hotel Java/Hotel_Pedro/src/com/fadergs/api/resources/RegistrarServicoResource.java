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

import com.fadergs.api.dao.DaoComprarServico;
import com.fadergs.api.dao.DaoSupplier;
import com.fadergs.api.model.ComprarServico;
import com.fadergs.api.model.Status;
import com.fadergs.api.resources.util.ResponseBuilderControl;


@Path("/registrarServico")
@Produces("application/json")
public class RegistrarServicoResource {
	//INICIO DESIGNAR SERVIÇO
	
		@POST
		@Path("/registrar")
		public Response comprarServico(ComprarServico comprServ) {
			Response.ResponseBuilder resBuilder = null;
		
			try {
				resBuilder = Response.ok().entity(DaoSupplier.getDaoComprarServico().insert(comprServ));
			} catch (Exception e) {
				resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
				e.printStackTrace();
			}
			
			return ResponseBuilderControl.allowOrigin(resBuilder).build();
		}
		
		@GET
		@Path("/buscar")
		public Response getRegistroServ(@QueryParam("id") int id) {
			Response.ResponseBuilder resBuilder = null;
			ComprarServico compServ = new DaoComprarServico().findById(id);

			if (compServ != null) {
				resBuilder = Response.ok().entity(compServ);
			} else {
				resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
			}

			return ResponseBuilderControl.allowOrigin(resBuilder).build();
		}
		
		@GET
		@Path("/buscarTodos")
		public Response getRegistrosServico(@QueryParam("id") int id) {
			Response.ResponseBuilder resBuilder = null;
			List<ComprarServico> comp = DaoSupplier.getDaoComprarServico().findAll();

			resBuilder = Response.ok().entity(comp);
			

			return ResponseBuilderControl.allowOrigin(resBuilder).build();
		}
		
		@DELETE
		@Path("/delete")
		public Response deleteServico(@QueryParam("id") int id) {
			Response.ResponseBuilder resBuilder = null;
			
			try {
				new DaoComprarServico().delete(id);
				resBuilder = Response.ok().entity(new Status(true, Status.DELETADO));
			} catch (Exception e) {
				resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
			}
			return ResponseBuilderControl.allowOrigin(resBuilder).build();
		}

		@PUT
		@Path("/update")
		public Response updateServico(ComprarServico comprarServ) {
			Response.ResponseBuilder resBuilder = null;
		
			try {
				resBuilder = Response.ok().entity(DaoSupplier.getDaoComprarServico().update(comprarServ));
			} catch (Exception e) {
				resBuilder = Response.ok().status(500).entity(new Status(e.toString()));
				e.printStackTrace();
			}
			
			return ResponseBuilderControl.allowOrigin(resBuilder).build();
		}
		
		//=============================================
		//FIM DESIGNAR SERVICO
		//=============================================
}
