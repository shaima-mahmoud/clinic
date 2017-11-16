package com.clinic.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Entity
@Table(name = "patient")
@NamedQueries(value={
		@NamedQuery(name = "SearchPatientByName" , query="from Patient p where p.user.name like :name"),
		@NamedQuery(name="GetPatientById", query="from Patient p where p.id = :id"),
		@NamedQuery(name="GetPatientByUsername", query="from Patient p where p.user.username = :username")
})
public class Patient implements Serializable{
	
	private static final long serialVersionUID = -5440526122555924318L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name="user_id")
	@OneToOne(cascade =  CascadeType.ALL)
	private User user;

	@Column
	private Double value;

	@Column
	private Double paid;
	
	public Patient() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getPaid() {
		return paid;
	}

	public void setPaid(Double paid) {
		this.paid = paid;
	}
	
}
