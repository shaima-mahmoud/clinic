package com.clinic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Entity
@Table(name = "user")
@NamedQueries(value={
		@NamedQuery(name="GetAllUsers" , query="from User u where u.type = :userType"),
		@NamedQuery(name="GetUserById" , query="from User u where u.id= :userid"),
		@NamedQuery(name="GetUserByUsernameAndPassword", query="from User u where u.username= :username and u.password = :password and u.type = :type")
})
public class User implements Serializable{
	
	private static final long serialVersionUID = 728455970104173389L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	// 1 admin , 2 patient , 3 doctor
	@Column(name="user_type_id")
	private Integer type;

	@Column(name="image_url")
	private String imageURL;
	
	
	public User() {
		super();
	}


	public User(Long id) {
		this.id = id;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getImageURL() {
		return imageURL;
	}


	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
}
