package com.example.demo.response;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
//replace all these with @data. then create a special constructor to use with the service.
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@Data
@AllArgsConstructor()
public class StudentResponse {
//	@JsonIgnore
	private long  id;
	@JsonProperty("first_Name")
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String state;
	private Address address; // lets see how this looks as an object
	
	public StudentResponse(Student s) {
		this.id = s.getId();
		this.firstName = s.getFirstName();
		this.lastName = s.getLastName();
		this.email = s.getEmail();
		this.city = s.getAddress().getCity();
		this.state = s.getAddress().getState();
		this.address = s.getAddress();
	}
	public StudentResponse(Address a) {
		this.id = a.getStudent().getId();
		this.firstName = a.getStudent().getFirstName();
		this.lastName = a.getStudent().getLastName();
		this.email = a.getStudent().getEmail();
		this.city = a.getCity();
		this.state = a.getState();
//		this.address = a;
	}
	
}
