package com.example.demo.request;

import javax.validation.constraints.NotBlank;

import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateStudentRequest {
	@JsonProperty("first_Name")
    @NotBlank(message = "First Name Is Required")
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String state;
	
}
