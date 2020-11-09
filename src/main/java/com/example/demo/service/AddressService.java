package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.example.repository.AddressRepository;
import com.demo.example.repository.StudentRepository;
import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.UpdateStudentRequest;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepo;
	
	public List<Address> getAddressById(Long id) {
		List<Address> results = addressRepo.findAllById(id);
		return results;
	}

}
