package com.example.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;







@Entity

public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@Column(unique = true)
	private String username;
	
	@JsonIgnore
	//loai bo property khi tra ve doi tuong
	
	private String password;
	
	@Column(unique = true)
	private String email;
	
	private String phone;
	
	private Timestamp reg_date;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
			name = "account_role",
			joinColumns = @JoinColumn(name="account_id"),
			inverseJoinColumns  = @JoinColumn(name="role_id")
		)
			  
	private Set<Role> roles;
	

	public Account() {
		
	}


	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public Timestamp getReg_date() {
		return reg_date;
	}



	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}




	public Set<Role> getRoles() {
		return roles;
	}




	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	
	
	
	
	
	
	
	
	
}
