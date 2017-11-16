package com.clinic.controller;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.clinic.entity.User;
import com.clinic.service.CliniqueService;
 
@Path("/user")
public class UserController {
	
	private static CliniqueService service = new CliniqueService();

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		try {
			User loginuser = service.loginUser(user);
			String output = "Jersey say: Login = " + loginuser;
			if(loginuser != null)
				return Response.status(200).entity(loginuser).build();
			else 
				return Response.status(400).entity("Username or password is incorrect").build();
		} catch (Exception e) {
			return Response.status(400).entity("Cann't login, error occured").build();
		}
	}
 
}