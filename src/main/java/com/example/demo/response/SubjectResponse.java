package com.example.demo.response;

import java.util.List;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
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
public class SubjectResponse {
//	@JsonIgnore
	private long  id;
	@JsonProperty("first_Name")
	private String name;
	private Double marks;
	
	public SubjectResponse(Subject sub) {
		this.id = sub.getId();
		this.name = sub.getSubjectName();
		this.marks = sub.getMarks();
	}
}
