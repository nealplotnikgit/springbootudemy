package com.example.demo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class UpdateStudentRequest {
    @NotNull(message = "ID Is Required")
    private Long id;
	@JsonProperty("first_Name")
	private String firstName;
	private String lastName;
	private String email;
	
}
