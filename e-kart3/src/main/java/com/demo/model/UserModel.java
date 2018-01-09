package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "sekhar_ekart")
public class UserModel {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@JsonProperty
	@NotEmpty(message="name field mandatory")
    private String name;
	
	@Column(unique=true)
	@Email(message="Please provide valid email")
	@NotEmpty(message="email mandatory")
	@JsonProperty
    private String email;
	
	@NotEmpty(message="password mandatory")
	@Length(min=8,max=50,message="password length should be in limit(8 to 15) character's ")
    @JsonProperty
    private String password;
    
    private int active;
    
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	private String salt;
    
    
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", active="
				+ active + ", salt=" + salt + "]";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
