package com.clinic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "doctor_availability")
@NamedQueries(value={
		@NamedQuery(name="GetDoctorAvailabilitySlots", query="from DoctorAvailability v where v.doctor.id= :doctorid"),
		@NamedQuery(name="DeleteDoctorAvailabilitySlot", query="delete from DoctorAvailability v where v.id= :slotid"),
		
		@NamedQuery(name="DeleteAllDoctorAvailability", query="delete from DoctorAvailability v where v.doctor.id= :doctorid"),
		@NamedQuery(name="GetDoctorSlotsNotIn", query="from DoctorAvailability v where v.doctor.id= :doctorid and v.id not in (:slotsToKeep))"),
		@NamedQuery(name="DeleteDoctorAvailabilitySlots", query="delete from DoctorAvailability v where v.doctor.id= :doctorid and v.id not in (:slots)")
})
public class DoctorAvailability implements Serializable{
	
	private static final long serialVersionUID = -4792870836934491599L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String day;
	
	@Column
	private Double hour;

	// sol 2 
	@JoinColumn(name="doctor_id")
	@ManyToOne
	@JsonIgnore
	private Doctor doctor;
	
	public DoctorAvailability() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Double getHour() {
		return hour;
	}

	public void setHour(Double hour) {
		this.hour = hour;
	}

	@JsonIgnore
	public Doctor getDoctor() {
		return doctor;
	}

	@JsonIgnore
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
