package com.clinic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.clinic.dao.HibernateUtil;
import com.clinic.dto.AdminMessage;
import com.clinic.dto.ResposneMessage;
import com.clinic.entity.Doctor;
import com.clinic.entity.DoctorAvailability;
import com.clinic.entity.Message;
import com.clinic.entity.Patient;
import com.clinic.entity.Prescription;
import com.clinic.entity.Reservation;
import com.clinic.entity.User;
import com.clinic.entity.UserType;

public class CliniqueService {

	private static HibernateUtil dao = new HibernateUtil();

	////////////////////////////////////////////////
	public User loginUser(User user) {
		User existinguser = dao.getUser(user);
		if (user != null)
			return existinguser;
		else
			return null;
	}

	///////////////////////////////////////////////

	public void saveDoctor(Doctor doctor) {

		if (doctor.getAvailabilitySlots() != null && !doctor.getAvailabilitySlots().isEmpty()) {
			Iterator<DoctorAvailability> it = doctor.getAvailabilitySlots().iterator();
			while (it.hasNext()) {
				DoctorAvailability slot = it.next();
				slot.setDoctor(doctor);
			}
		}
		
		if(doctor.getId()!=null)
		dao.deleteAllOldSlots(doctor.getId());
		
		if (doctor.getId() == null) {
			if (doctor.getUser() != null)
				doctor.getUser().setType(UserType.DOCTOR);
			dao.createDoctor(doctor);
		} else
			dao.updateDoctor(doctor);
	}

	public Doctor getDoctor(Long doctorId) {
		if (doctorId != null) {
			Doctor d = dao.getDoctor(doctorId);
			return d;
		}
		return null;
	}

	public Doctor getDoctorByUserId(Long userid) {
		if (userid != null) {
			Doctor d = dao.getDoctorByUserId(userid);
			return d;
		}
		return null;
	}

	public Doctor getDoctor(String username) {
		if (username != null) {
			Doctor d = dao.getDoctor(username);
			return d;
		}
		return null;
	}

	public List<Doctor> searchDoctors(String name, String spec) {
		List<Doctor> doctors;
		if (name == null)
			doctors = dao.searchDoctor("");
		else
			doctors = dao.searchDoctor(name);
		for (Doctor d : doctors)
			d.setAvailabilitySlots(dao.getDoctorAvailabilitySlots(d.getId()));
		return doctors;
	}
	///////////////////////////////////////////////

	public List<Patient> searchPatients(String name) {
		List<Patient> patients;
		if (name == null)
			patients = dao.searchPatients("");
		else
			patients = dao.searchPatients(name);
		return patients;
		
	}

	public Patient getPatient(Long id) {
		if (id != null)
			return dao.getPatient(id);
		return null;
	}

	public Patient getPatient(String username) {
		if (username != null) {
			Patient p = dao.getPatient(username);
			return p;
		} else
			return null;
	}

	
	public void savePatient(Patient patient) {
		if (patient.getId() == null) {
			if (patient.getUser() != null)
				patient.getUser().setType(UserType.PATIENT);
			dao.createPatient(patient);
		} else {
			dao.updatePatient(patient);
		}
	}

	///////////////////////////////////////////////
	
	public void broadcast(ResposneMessage msg, Long adminuserid) {
		if(msg!=null){
		List<User> receivers = dao.getAllUsers(UserType.PATIENT);
		List<Message> messages = new ArrayList<Message>();
		for (User receiver : receivers)
			messages.add(new Message(null, new User(adminuserid), receiver, msg.getMessage(), new Date()));
		dao.saveMessages(messages);
		}
	}

	public void sendMessage(AdminMessage msg, Long adminuserid) {
		List<Message> messages = new ArrayList<Message>();

		for (Long receiverId : msg.getUserIds()) {
			User receiver = dao.getUser(receiverId);
			if (receiver != null)
				messages.add(new Message(null, new User(adminuserid), receiver, msg.getMessage(), new Date()));
			else
				continue;
		}
		dao.saveMessages(messages);
	}
	///////////////////////////////////////////////

	public List<Reservation> getPatientReservations(Long patientid) {
		if (patientid == null)
			return null;
		List<Reservation> res = dao.getPatientReservations(patientid);
		return res;
	}

	public String saveResesrvation(Reservation reservation) {
		String reservationCode;
		if (reservation.getPrescriptions() != null && !reservation.getPrescriptions().isEmpty()) {
			Iterator<Prescription> it = reservation.getPrescriptions().iterator();
			while (it.hasNext()) {
				Prescription pres = it.next();
				pres.setReservation(reservation);
			}
		} if (reservation.getId() == null) {
			Long maxResId = dao.getMaxReservationId();
			reservationCode = "VS_" + reservation.getPatient().getId() + "" + (maxResId + 1);
			reservation.setReservationCode(reservationCode);
			dao.saveReservation(reservation);
			return reservationCode;
		} else {
			dao.saveReservation(reservation);
			return reservation.getReservationCode();
		}
	}

	public List<Message> getMessagesTo(Long userid) {
		if (userid == null)
			return null;
		List<Message> msgs = dao.getMessagesTo(userid);
		return msgs;
	}

	public Reservation getReservationByCode(String reservationcode) {
		if (reservationcode == null)
			return null;
		Reservation reservation = dao.getReservationByCode(reservationcode);
		return reservation;
	}

	public List<Reservation> getDoctorScheduleOfCurrentMonth(Long doctorid) {
		if (doctorid == null)
			return null;
		List<Reservation> res = dao.getReservationsByDoctorId(doctorid);
		return res;
	}

	public void saveAdminUser(User user) {
		if (user.getId() == null) {
			user.setType(UserType.ADMIN);
			dao.createAdminUser(user);
		} else {
			user.setType(UserType.ADMIN);			
			dao.updateAdminUser(user);
		}
	}

	public User getAdminUser(Long userid) {
		if(userid == null)
			return null;
		User user = dao.getAdminUser(userid);
		return user;
	}

}
