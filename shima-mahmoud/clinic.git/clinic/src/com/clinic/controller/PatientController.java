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

import com.clinic.dto.ResposneMessage;
import com.clinic.entity.Doctor;
import com.clinic.entity.Message;
import com.clinic.entity.Patient;
import com.clinic.entity.Reservation;
import com.clinic.service.CliniqueService;

@Path("/patient")
public class PatientController {

	private static CliniqueService service = new CliniqueService();

	@POST
	@Path("/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePatient(Patient patient) {
		try {
			service.savePatient(patient);
			String output = "Jersey say: register Patient";
			return Response.status(200).entity(new ResposneMessage("Saved Successfully")).build();
		} catch (ConstraintViolationException e) {
			return Response.status(400).entity(new ResposneMessage("Patient username or email is used before")).build();
		} catch(Exception ex ){
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't save Patient")).build();
		} 
		
	}

	
	@GET
	@Path("/profile/{username}")
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

	@POST
	@Path("/reservation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveReservation(Reservation reservation) {
		try {
			String code = service.saveResesrvation(reservation);
			String output = "Jersey say: registerPatient";
			return Response.status(200).entity(code).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't save Reservation")).build();
		}
	}

	@GET
	@Path("/reservation/{patientId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservations(@PathParam("patientId") Long patientId) {
		try {
			List<Reservation> res = service.getPatientReservations(patientId);
			String output = "Operation: Get Patient Reservations";
			return Response.status(200).entity(res).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error Occured, Couldn't retrieve Reservations")).build();
		}
	}

	@GET
	@Path("/reservation/code/{reservationcode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservationByCode(@PathParam("reservationcode") String reservationcode) {
		try {
			Reservation res = service.getReservationByCode(reservationcode);
			String output = "Operation: Get Patient Reservation By Code";
			return Response.status(200).entity(res).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error Occured, Couldn't retrieve Reservations")).build();
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
	@Path("/message/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages(@PathParam("userid") Long userid) {
		try {
			List<Message> messages = service.getMessagesTo(userid);
			String output = "Jersey say: getMessages";
			return Response.status(200).entity(messages).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't retreive inbox")).build();
		}
	}

	
}