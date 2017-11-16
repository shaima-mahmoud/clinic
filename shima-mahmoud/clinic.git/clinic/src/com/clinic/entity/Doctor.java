package com.clinic.entity;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "doctor")

@NamedQueries(value = {
		@NamedQuery(name="SearchDoctorByName", query="from Doctor d where d.user.name like :name"),
		@NamedQuery(name="GetDoctorById", query="from Doctor d where d.id = :doctorid"),
		@NamedQuery(name="GetDoctorByUsername", query="from Doctor d where d.user.username = :username"),
		@NamedQuery(name="GetDoctorByUserId", query="from Doctor d where d.user.id = :userid"),
		
})
public class Doctor implements Serializable{
	
	private static final long serialVersionUID = -1995492170981165462L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column
	private String address;
	
	@Column
	private Double location_long;
	
	@Column
	private Double location_lat;
	
	@JoinColumn
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	@JoinColumn(name="specialization_id")
	@ManyToOne
	private Specialization specialization;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	Set<DoctorAvailability> availabilitySlots;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLocation_long() {
		return location_long;
	}

	public void setLocation_long(Double location_long) {
		this.location_long = location_long;
	}

	public Double getLocation_lat() {
		return location_lat;
	}

	public void setLocation_lat(Double location_lat) {
		this.location_lat = location_lat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public Set<DoctorAvailability> getAvailabilitySlots() {
		return availabilitySlots;
	}

	public void setAvailabilitySlots(Set<DoctorAvailability> availabilitySlots) {
		this.availabilitySlots = availabilitySlots;
	}
	
}
