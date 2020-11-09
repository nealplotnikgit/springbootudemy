package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.request.CreateStudentRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor //need this because creating the explicit constructor will not create the default one, but this is needed for JPA
@Table(name = "student")
//test git
//default constructor is required - already provided since no other is defined
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long  id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	
//	@OneToOne(fetch = FetchType.LAZY)
	@OneToOne(fetch = FetchType.EAGER) //this is default
	// using the join on the owning side creates a relationship to the child table
	@JoinColumn(name = "address_id")
	private Address address;
	
	public Student(CreateStudentRequest csr) {
		this.firstName = csr.getFirstName();
		this.lastName = csr.getLastName();
		this.email = csr.getEmail();
	}
}
