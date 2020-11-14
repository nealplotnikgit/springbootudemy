package com.example.demo.service;

import java.util.ArrayList;
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
import com.demo.example.repository.SubjectRepository;
import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.CreateSubjectRequest;
import com.example.demo.request.UpdateStudentRequest;


@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private SubjectRepository subjectRepo;
	
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	};
	
	public Student add(CreateStudentRequest csr) {
		Student student = new Student(csr);
		
		Address address = new Address();
		address.setCity(csr.getCity());
		address.setState(csr.getState());
		address = addressRepo.save(address);
		
		student.setAddress(address);//adds the address which contains the generated ID so that it joins
		student = studentRepo.save(student);
		
		List subjectList = new ArrayList();
		if (csr.getSubjects() != null) {
			for (CreateSubjectRequest subjectRequest: csr.getSubjects()) {
				Subject s = new Subject();
				s.setMarks(subjectRequest.getMarks());
				s.setSubjectName(subjectRequest.getName());
				s.setStudent(student);
				subjectList.add(s); //create a list for each subject
			}
		List<Subject> subjects = subjectRepo.saveAll(subjectList);
		student.setSubjects(subjects);
		}
		
		return student;
	}


	public Student update(UpdateStudentRequest updateStudentRequest) {
		Student student = studentRepo.findById(updateStudentRequest.getId()).get();
		if (updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isBlank()) {
			student.setFirstName(updateStudentRequest.getFirstName());
		}
		if (updateStudentRequest.getLastName() != null&& !updateStudentRequest.getLastName().isBlank()) {
			student.setLastName(updateStudentRequest.getLastName());
		}
		if (updateStudentRequest.getEmail() != null && !updateStudentRequest.getEmail().isBlank()) {
			student.setEmail(updateStudentRequest.getEmail());
		}
		student = studentRepo.save(student);
		return student;
	}

	public String delete(Long id) {
		studentRepo.deleteById(id);
		return "student " + id + " deleted";
	}
	public List<Student> getByFirstName(String firstName) {
		List<Student> results = studentRepo.findByFirstName(firstName);
		return results;
	}
	public Student getByFullName(String firstName,String lastName) {
		return studentRepo.findByFirstNameAndLastName(firstName, lastName);
	}
	public Student getByFirstNameAndLastName(String firstName,String lastName) {
		return studentRepo.getByLastNameAndFirstName(lastName, firstName);
	}
	public List<Student> getByFirstNameOrLastName(String firstName,String lastName) {
		return studentRepo.findByFirstNameOrLastName(firstName, lastName);
	}
	public List<Student> getByFirstNameIn(List<String> in) {
		return studentRepo.findByFirstNameIn(in);
	}

	public List<Student> getAllStudentsPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize); //zero based index
		return studentRepo.findAll(pageable).getContent();
	}
	public List<Student> getAllStudentsSorted() {
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName","lastName");
		return studentRepo.findAll(sort);
	}

	public Integer updateFirstName(Long id, String firstName) {
		return studentRepo.updateFirstName(id,firstName);
	}
	public Integer deleteFirstName(String firstName) {
		return studentRepo.deleteFirstName(firstName);
	}

	public List<Student> getByState(String state) {
		return studentRepo.findByAddressState(state);
		//return null;
	}

	public List<Student> getByStateJPQL(String state) {
		return studentRepo.getByState(state);
	}

}
