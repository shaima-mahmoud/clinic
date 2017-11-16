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
import com.clinic.entity.Patient;
import com.clinic.entity.Reservation;
import com.clinic.service.CliniqueService;
 
@Path("/doctor")
public class DoctorController {
	
	private static CliniqueService service = new CliniqueService();	
	
	@POST
	@Path("/patient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePatient(Patient patient) {
		try {
			service.savePatient(patient);
			String output = "Jersey say: Update Patient Payment";
			return Response.status(200).entity(new ResposneMessage("Saved Successfully")).build();
		} catch (ConstraintViolationException e) {
			return Response.status(400).entity(new ResposneMessage("Patient username or email is used before")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't save Patient")).build();
		}
	}
	
	@POST
	@Path("/patient/reservation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveReservation(Reservation reservation) {
		try {
			String code = service.saveResesrvation(reservation);
			String output = "Jersey say: updatePatientReservation";
			return Response.status(200).entity(code).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't save Reservation")).build();
		}
	}
	
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

	@GET
	@Path("/patient/reservation/code/{reservationcode}")
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
	@Path("/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveDoctor(Doctor doctor) {
		try {
			service.saveDoctor(doctor);
			String output = "Jersey say: saveDoctor " ;
			return Response.status(200).entity(new ResposneMessage("Saved Successfully")).build();
		} catch (ConstraintViolationException e) {
			return Response.status(400).entity(new ResposneMessage("Doctor username or email is used before")).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Error occured, Couldn't save doctor")).build();
		}
	}
	
	@GET
	@Path("/profile/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDoctor(@PathParam("userid") Long userid) {
		try {
			Doctor doctor = service.getDoctorByUserId(userid);
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
	@Path("/schedule/{doctorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDoctorSchedule(@PathParam("doctorid") Long doctorid) {
		try {
			List<Reservation> scehdule= service.getDoctorScheduleOfCurrentMonth(doctorid);
			String output = "Jersey say: getDoctor by id";
				return Response.status(200).entity(scehdule).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResposneMessage("Couldn't retreive doctor schedule")).build();
		}
	}
	
	
}

