package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor //need this because creating the explicit constructor will not create the default one, but this is needed for JPA
@Table(name = "address")
//default constructor is required - already provided since no other is defined
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "id")
	private Long  id;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	//bidirectional relationship. since this is the non owning class, use mapped by, creates relationship to the parent / owning table
	@OneToOne(mappedBy="address")
	private Student student;
	
}
