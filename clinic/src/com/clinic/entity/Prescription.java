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

@Entity
@Table(name = "prescription")
public class Prescription implements Serializable{

	private static final long serialVersionUID = -4163365613991072062L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="description")
	private String title;
	
	@JoinColumn(name="patient_reservation_id")
	@ManyToOne
	@JsonIgnore
	private Reservation reservation;

	@Column(name="test_result")
	private String resultURL;
	
	
	public Prescription() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public Reservation getReservation() {
		return reservation;
	}

	@JsonIgnore
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
	
}
