package com.clinic.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.clinic.entity.Doctor;
import com.clinic.entity.DoctorAvailability;
import com.clinic.entity.Message;
import com.clinic.entity.Patient;
import com.clinic.entity.Reservation;
import com.clinic.entity.User;

public class HibernateUtil {

	private static Session session = ((SessionFactory) (new Configuration().configure()).buildSessionFactory())
			.openSession();

	public void createDoctor(Doctor doctor) {
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(doctor);
			session.flush();
			session.clear();
			transaction.commit();
			System.out.println("\n Doctor Details Added \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			session.clear();
		}
	}

	public void updateDoctor(Doctor doctor) {
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(doctor);
			session.flush();
			session.clear();
			transaction.commit();
			System.out.println("\n Doctor Details Added \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			session.clear();
		}
	}

	public List<Doctor> searchDoctor(String name) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("SearchDoctorByName");
			q.setString("name", "%" + name + "%");

			List<Doctor> doctors = q.list();

			System.out.println("\n Doctor Details Retrieved \n");
			return doctors;
		} catch (HibernateException e) {
			throw e;
		}
	}

	public Doctor getDoctor(Long id) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetDoctorById");
			q.setLong("doctorid", id);
			List<Doctor> doctors = q.list();
			System.out.println("\n Doctor Details Retrieved \n");
			if (doctors.isEmpty())
				return null;
			else
				return doctors.get(0);
		} catch (HibernateException e) {
			throw e;
		}
	}

	public Doctor getDoctor(String username) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetDoctorByUsername");
			q.setString("username", username);
			List<Doctor> doctors = q.list();
			System.out.println("\n Doctor Details Retrieved \n");
			if (doctors.isEmpty())
				return null;
			else
				return doctors.get(0);
		} catch (HibernateException e) {
			throw e;
		}
	}

	
	public Doctor getDoctorByUserId(Long userid) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetDoctorByUserId");
			q.setLong("userid", userid);
			List<Doctor> doctors = q.list();
			System.out.println("\n Doctor Details Retrieved \n");
			if (doctors.isEmpty())
				return null;
			else
				return doctors.get(0);
		} catch (HibernateException e) {
			throw e;
		}
	}
	
	
	public List<Patient> searchPatients(String name) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("SearchPatientByName");
			q.setString("name", "%" + name + "%");

			List<Patient> patients = q.list();

			System.out.println("\n Patients Details Retrieved \n");
			return patients;
		} catch (HibernateException e) {
			throw e;
		}
	}

	public Patient getPatient(String username) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetPatientByUsername");
			q.setString("username", username);
			List<Patient> patients = q.list();
			System.out.println("\n Patient Details Retrieved \n");
			if (patients.isEmpty())
				return null;
			else
				return patients.get(0);
		} catch (HibernateException e) {
			throw e;
		}
	}

	public Patient getPatient(Long id) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetPatientById");
			q.setLong("id", id);

			List<Patient> patients = q.list();

			System.out.println("\n Patient Details Retrieved \n");
			if (patients.isEmpty())
				return null;
			else
				return patients.get(0);
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void createPatient(Patient patient) {
		try {
			Transaction transaction = session.beginTransaction();
			// session.save(patient);
			session.merge(patient);
			session.flush();
			transaction.commit();
			System.out.println("\n Patient Details Added \n");
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void updatePatient(Patient patient) {
		try {
			Transaction transaction = session.beginTransaction();
			// session.update(patient);
			session.merge(patient);
			session.flush();
			transaction.commit();
			System.out.println("\n Patient Details Added \n");
		} catch (HibernateException e) {
			throw e;
		}
	}


	public List<User> getAllUsers(Integer userType) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetAllUsers");
			q.setInteger("userType", userType);
			List<User> users = q.list();
			System.out.println("\n Patient Users Retrieved \n");
			return users;
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void saveMessages(List<Message> messages) {
		try {
			Transaction transaction = session.beginTransaction();
			for (Message m : messages)
				session.save(m);
			transaction.commit();
			System.out.println("\n Admin Message Added \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public List<Reservation> getPatientReservations(Long patientid) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetPatientReservations");
			q.setLong("patientid", patientid);

			List<Reservation> res = q.list();

			System.out.println("\n Patient Reservations Retrieved \n");
			return res;
		} catch (HibernateException e) {
			throw e;
		}
	}

	public Set<DoctorAvailability> getDoctorAvailabilitySlots(Long doctorid) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetDoctorAvailabilitySlots");
			q.setLong("doctorid", doctorid);
			List<DoctorAvailability> avail = q.list();
			System.out.println("\n Doctor Availability Slots Retrieved \n");
			return new HashSet<DoctorAvailability>(avail);
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void createAvailabilitySlot(DoctorAvailability slot) {
		try {
			Transaction transaction = session.beginTransaction();
			// session.save(slot);
			session.merge(slot);
			session.flush();
			transaction.commit();
			System.out.println("\n Doctor Details Added \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void deleteOtherSlot(Long slotid) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("DeleteDoctorAvailabilitySlot");
			q.setLong("slotid", slotid);
			transaction.begin();
			int i = q.executeUpdate();
			session.flush();
			transaction.commit();
			session.clear();
			System.out.println("\n Doctor Availability Exess Slots Deleted with Result {" + i + "}\n");

		} catch (HibernateException e) {
			throw e;
		}
	}
	
	
	public void deleteOtherSlots(Long doctorid, List<Long> slotsToKeep) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("DeleteDoctorAvailabilitySlots");
			q.setLong("doctorid", doctorid);
			q.setParameterList("slots", slotsToKeep);
			transaction.begin();
			int i = q.executeUpdate();
			session.flush();
			transaction.commit();
			session.clear();
			System.out.println("\n Doctor Availability Exess Slots Deleted with Result {" + i + "}\n");

		} catch (HibernateException e) {
			throw e;
		}
	}

	public User getUser(Long receiverId) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetUserById");
			q.setLong("userid", receiverId);

			List<User> users = q.list();

			System.out.println("\n User Details Retrieved \n");
			if (users.isEmpty())
				return null;
			else
				return users.get(0);
		} catch (HibernateException e) {
			throw e;
		}
	}

	public List<Message> getMessagesTo(Long userid) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetMessagesTo");
			q.setLong("userid", userid);
			List<Message> msgs = q.list();
			System.out.println("\n Inbox Messages Retrieved \n");
			return msgs;
		} catch (HibernateException e) {
			throw e;
		}
	}
	
	public List<Message> getMessagesFrom(Long userid) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetMessagesFrom");
			q.setLong("userid", userid);
			List<Message> msgs = q.list();
			System.out.println("\n Sent Messages Retrieved \n");
			return msgs;
		} catch (HibernateException e) {
			throw e;
		}
	}
	
	public void savePatient(Reservation reservation) {
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(reservation);
			session.flush();
			transaction.commit();
			session.clear();
			System.out.println("\n Reservation Details Added \n");
		} catch (HibernateException e) {
			throw e;
		}
	}

	public User getUser(User user) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetUserByUsernameAndPassword");
			q.setString("username", user.getUsername());
			q.setString("password", user.getPassword());
			q.setInteger("type", user.getType());
			List<User> users = q.list();
			
			if (users.isEmpty()){
				System.out.println("\n Users not found \n");
				return null;
			}
			else{
				System.out.println("\n Users Retrieved \n");
				return users.get(0);
				}
		} catch (HibernateException e) {
			throw e;
		}
	}

	public Reservation getReservationByCode(String reservationcode) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetReservationByCode");
			q.setString("reservationcode", reservationcode);
			List<Reservation> res = q.list();
			System.out.println("\n Users Retrieved \n");
			if (res.isEmpty())
				return null;
			else
				return res.get(0);
		} catch (HibernateException e) {
			throw e;
		}
	}

	public List<Reservation> getReservationsByDoctorId(Long doctorid) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetDoctorReservationsByMonth");
			q.setLong("doctorid", doctorid);
			List<Reservation> res = q.list();
			System.out.println("\n Users Retrieved \n");
			return res;
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void saveReservation(Reservation reservation) {
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(reservation);
			session.flush();
			transaction.commit();
			System.out.println("\n Reservation Details Added \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public Long getMaxReservationId() {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetMaxReservationId");
			Long res =(Long)q.uniqueResult();
			System.out.println("\n Data Retrieved \n");
			return res;
		} catch (HibernateException e) {
			throw e;
		}
	}

	public List<DoctorAvailability> getAllDoctorSlotsnotIn(Long doctorid, List<Long> slotsToKeep) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetDoctorSlotsNotIn");
			q.setLong("doctorid", doctorid);
			q.setParameterList("slotsToKeep", slotsToKeep);
			List<DoctorAvailability> res = q.list();
			System.out.println("\n Slots Retrieved \n");
			return res;
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void deleteAllOldSlots(Long doctorid) {
		
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("DeleteAllDoctorAvailability");
			q.setLong("doctorid", doctorid);
			q.executeUpdate();
			session.flush();
			transaction.commit();
			System.out.println("\n All SLots Deleted \n");
			
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void deleteSlot(DoctorAvailability slot) {
		try {
			Transaction transaction = session.beginTransaction();
			session.delete(slot);
			session.flush();
			transaction.commit();
			System.out.println("\n Slot Deleted \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void createAdminUser(User user) {
		try {
			Transaction transaction = session.beginTransaction();
			session.save(user);
			session.flush();
			session.clear();
			transaction.commit();
			System.out.println("\n Admin User Saved \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void updateAdminUser(User user) {
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(user);
			session.flush();
			session.clear();
			transaction.commit();
			System.out.println("\n Admin User Saved \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public User getAdminUser(Long userid) {
		try {
			Transaction transaction = session.beginTransaction();
			Query q = session.getNamedQuery("GetUserById");
			q.setLong("userid", userid);
			List<User> res = q.list();
			System.out.println("\n User Retrieved \n");
			if (res.isEmpty())
				return null;
			else
				return res.get(0);
		} catch (HibernateException e) {
			throw e;
		}
	}
}
