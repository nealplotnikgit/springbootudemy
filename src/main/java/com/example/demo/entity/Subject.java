package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "subject")
//test git
//default constructor is required - already provided since no other is defined
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long  id;
	@Column(name = "subject_name")
	private String subjectName;
	@Column(name = "marks_obtained")
	private Double marks;
	
	
	@ManyToOne(fetch = FetchType.EAGER) //this is default
	// using the join on the owning side creates a relationship to the child table. 
	//this table is the owner because it contains the foreign key to the student
	@JoinColumn(name = "student_id")
	private Student student;
	
	
}
