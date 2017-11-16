package com.clinic.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "patient_reservation")
@NamedQueries(value = {
		@NamedQuery(name = "GetPatientReservations", query = "from Reservation r where r.patient.id = :patientid"),
		@NamedQuery(name = "GetDoctorReservationsByMonth", query = "from Reservation r where r.doctor.id= :doctorid and month(r.dateandtime)= month (now())"),
		@NamedQuery(name = "GetMaxReservationId", query = "select max(r.id) from Reservation r"),
		@NamedQuery(name = "GetReservationByCode", query ="from Reservation r where r.reservationCode= :reservationcode"),
		
		}
)
public class Reservation implements Serializable {

	private static final long serialVersionUID = -6169539639812190727L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Date dateandtime;

	@JoinColumn(name = "doctor_availability_id")
	@ManyToOne
	private DoctorAvailability doctorAvailability;

	@JoinColumn(name = "doctor_id")
	@ManyToOne
	private Doctor doctor;

	@Column(name = "reservation_code")
	private String reservationCode;

	@JoinColumn(name = "patient_id")
	@ManyToOne
	private Patient patient;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private Set<Prescription> prescriptions;

	public Reservation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(Date dateandtime) {
		this.dateandtime = dateandtime;
	}

	public DoctorAvailability getDoctorAvailability() {
		return doctorAvailability;
	}

	public void setDoctorAvailability(DoctorAvailability doctorAvailability) {
		this.doctorAvailability = doctorAvailability;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
