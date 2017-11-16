package com.clinic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialization")
public class Specialization implements Serializable {

	public static Specialization SPECIALIZATION1 = new Specialization(1L, "Osteologist"); // 3etham

	public static Specialization SPECIALIZATION2 = new Specialization(2L, "Gynecologist"); // nesa

	public static Specialization SPECIALIZATION3 = new Specialization(3L, "Pediatrician"); // atfal
	
	public static Specialization SPECIALIZATION4 = new Specialization(3L, "Surgeo"); // gera7a
	
	public static Specialization SPECIALIZATION5 = new Specialization(3L, "Dermatologist"); // geldeya
	
	public static Specialization SPECIALIZATION6 = new Specialization(3L, "Ophthalmologist"); // rmad
	
	public static Specialization SPECIALIZATION7 = new Specialization(3L, "Otolaryngologist"); // anf w ozon
	
	public static Specialization SPECIALIZATION8 = new Specialization(3L, "Cardiologist"); // alb
	
	public static Specialization SPECIALIZATION9 = new Specialization(3L, "Neurologist"); // mo5 w a3sab
	
	public static Specialization SPECIALIZATION10 = new Specialization(3L, "Radiologist"); // ashe3a
	
	public static Specialization SPECIALIZATION11 = new Specialization(3L, "Internal Medicine"); // batna
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	// unique
	private String name;

	public Specialization() {

	}

	public Specialization(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
