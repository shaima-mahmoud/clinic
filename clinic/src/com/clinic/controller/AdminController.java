package com.clinic.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.exception.ConstraintViolationException;

import com.clinic.dto.AdminMessage;
import com.clinic.dto.ResposneMessage;
import com.clinic.entity.Doctor;
import com.clinic.entity.Patient;
import com.clinic.entity.Reservation;
import com.clinic.entity.User;
import com.clinic.service.CliniqueService;

@Path("/admin")
public class AdminController {

	private static CliniqueService service = new CliniqueService();

	@POST
	@Path("/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveAdminUser(User user) {
		try {
			service.saveAdminUser(user);
			String output = "Jersey say: save User profile";
			return Response.status(200).entity(new ResposneMessage("Saved Successfully")).build();
		} catch (ConstraintViolationException e) {
			return Response.status(400).entity(new ResposneMessage("Username or email is used before")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't save doctor")).build();
		}
	}

	@GET
	@Path("/profile/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdminUser(@PathParam("userid") Long userid) {
		try {
			User user = service.getAdminUser(userid);
			String output = "Jersey say: getAdmin";
			if(user !=null)
				return Response.status(200).entity(user).build();
			else 
				return Response.status(400).entity(new ResposneMessage("This User ID doesn't exist")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't retreive doctors")).build();
		}
	}

	@POST
	@Path("/doctor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveDoctor(Doctor doctor) {
		try {
			service.saveDoctor(doctor);
			String output = "Jersey say: saveDoctor" ;
			return Response.status(200).entity(new ResposneMessage("Saved Successfully")).build();
		} catch (ConstraintViolationException e) {
			return Response.status(400).entity(new ResposneMessage("Doctor username or email is used before")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't save doctor")).build();
		}
	}

	@GET
	@Path("/doctor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDoctors(@QueryParam("name") String name, @QueryParam("specialization") String spec) {
		try {
			List<Doctor> doctors = service.searchDoctors(name, spec);
			String output = "Jersey say: getDoctors";
			return Response.status(200).entity(doctors).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't retreive doctors")).build();
		}
	}

	@GET
	@Path("/doctor/id/{doctorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDoctor(@PathParam("doctorid") Long id) {
		try {
			Doctor doctor = service.getDoctor(id);
			String output = "Jersey say: getDoctor by id";
			if(doctor!=null)
				return Response.status(200).entity(doctor).build();
			else 
				return Response.status(400).entity(new ResposneMessage("This doctor ID doesn't exist")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Couldn't retreive doctor")).build();
		}
	}

	@GET
	@Path("/doctor/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDoctor(@PathParam("username") String username) {
		try {
			Doctor doctor = service.getDoctor(username);
			String output = "Jersey say: getDoctor by username";
			if (doctor != null)
				return Response.status(200).entity(doctor).build();
			else 
				return Response.status(400).entity(new ResposneMessage("This doctor ID doesn't exist")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Couldn't retreive doctor")).build();
		}
	}

	///////////////////////////////////////////////////////////////////////////////
	@GET
	@Path("/patient")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPatients(@QueryParam("name") String name) {
		try {
			List<Patient> patients = service.searchPatients(name);
			String output = "Jersey say: searchPatients";
			return Response.status(200).entity(patients).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't retreive patients")).build();
		}
	}

	@POST
	@Path("/patient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePatient(Patient patient) {
		try {
			service.savePatient(patient);
			String output = "Jersey say: savePatient";
			return Response.status(200).entity(new ResposneMessage("Saved Successfully")).build();
		}catch (ConstraintViolationException e) {
			return Response.status(400).entity(new ResposneMessage("Patient username or email is used before")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't save Patient")).build();
		}
	}

	@GET
	@Path("/patient/id/{patientid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPatient(@PathParam("patientid") Long id) {
		try {
			Patient patient = service.getPatient(id);
			String output = "Jersey say: getPatient by id";
			if (patient != null)
				return Response.status(200).entity(patient).build();
			else
				return Response.status(400).entity(new ResposneMessage("This patient ID doesn’t exist")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Couldn't retreive patient")).build();
		}
	}

	@GET
	@Path("/patient/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPatient(@PathParam("username") String username) {
		try {
			Patient patient = service.getPatient(username);
			String output = "Jersey say: getPatient by username";
			if (patient != null)
				return Response.status(200).entity(patient).build();
			else
				return Response.status(400).entity(new ResposneMessage("This patient ID doesn’t exist")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Couldn't retreive patient")).build();
		}
	}

	////////////////////////////////////////////////////////////////////////////////

	@POST
	@Path("/message/{adminuserid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendMessage(AdminMessage msg, @PathParam("adminuserid") Long adminuserid) {
		try {
			service.sendMessage(msg, adminuserid);
			String output = "Jersey say: " + msg;
			return Response.status(200).entity(new ResposneMessage("Sent Successfully")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Sent Successfully")).build();
		}
	}

	@POST
	@Path("/broadcast/{adminuserid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response broadcast(ResposneMessage msg, @PathParam("adminuserid") Long adminuserid) {
		service.broadcast(msg, adminuserid);
		String output = "Jersey say: " + msg;
		return Response.status(200).entity(new ResposneMessage("Sent Successfully")).build();
	}

	///////////////////////////////////////////////////////////////////////////////

	@GET
	@Path("/patient/reservation/{patientid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPatientReservations(@PathParam("patientid") Long patientid) {
		try {
			List<Reservation> res = service.getPatientReservations(patientid);
			String output = "Operation: Get Patient Reservations";
			return Response.status(200).entity(res).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error Occured, Couldn't retrieve Reservations")).build();
		}
	}

}