package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.InBodyRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.AddressService;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	@Value("${demo.app.name: default app name}")
	private String appname;
	
	@Autowired
	private StudentService service;
	@Autowired
	private AddressService addressService;
	
	//fake one
	@GetMapping("/get")
//	@RequestMapping(value="/get",method = RequestMethod.GET)
	public StudentResponse getStudent() {
		StudentResponse s = new StudentResponse(1,appname,"LastName","x.com","fakecity","IL",new Address());
		return s;
		//	return "Hello Student" + appname;
	}
	@GetMapping("/getAll")
//	@RequestMapping(value="/getAll",method = RequestMethod.GET)
	public List<StudentResponse> getAllStudents() {
		List<Student> list = service.getAllStudents();
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		list.stream().forEach(student -> listresponse.add(new StudentResponse(student)));
		return listresponse;
	}
	@GetMapping("/getAllPage")
	public List<StudentResponse> getAllStudentsPagination(@RequestParam int pageNo,
														 @RequestParam int pageSize) {
		List<Student> list = service.getAllStudentsPagination(pageNo, pageSize);
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		list.stream().forEach(student -> listresponse.add(new StudentResponse(student)));
		return listresponse;
	
	}
	@GetMapping("/getAllSorted")
	public List<StudentResponse> getAllStudentsSorted() {
		List<Student> list = service.getAllStudentsSorted();
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		list.stream().forEach(student -> listresponse.add(new StudentResponse(student)));
		return listresponse;
	
	}

	
	
	@PostMapping("/add")
	public StudentResponse addStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
		Student student = service.add(createStudentRequest);
		return new StudentResponse(student);
	}
	@PutMapping("/update")
	public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
		Student student = service.update(updateStudentRequest);
		return new StudentResponse(student);
	}
	@PutMapping("/updateFirstName/{id}/{firstName}")
	public String updateStudentFirstName(@PathVariable Long id, @PathVariable String firstName) {
		Integer count = service.updateFirstName(id,firstName);
		return count + " records updated";
	}
	
	@DeleteMapping("/delete") //delete?id=4
	public String deleteStudent(@RequestParam("id") long id) {
		return service.delete(id);
	}
	@DeleteMapping("/deleteFirstName") //delete?firstName=Joe
	public String deleteStudentByFirstName(@RequestParam("firstName") String firstName) {
		int count = service.deleteFirstName(firstName);
		return "Deleted " + count + " records";
	}
	@DeleteMapping("/remove/{id}") //remove/4
	public String removeStudent(@PathVariable("id") long id) {
		return service.delete(id);
	}
	@GetMapping("/getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName(@PathVariable("firstName") String firstName) {
		List<Student> students = service.getByFirstName(firstName);
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		students.stream().forEach(student -> listresponse.add(new StudentResponse(student)));
		return listresponse;
				
	}
	@GetMapping("/getAddressByID/{id}")
	public List<StudentResponse> getAddressById(@PathVariable("id") Long id) {
		List<Address> addresses = addressService.getAddressById(id);
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		addresses.stream().forEach(address -> listresponse.add(new StudentResponse(address)));
		return listresponse;
				
	}
	@GetMapping("/getByFullName") //?full=xxxx&last=xxx
	public StudentResponse getByFullName(@RequestParam("first") String firstName, @RequestParam("last") String lastName) {
		return new StudentResponse(service.getByFullName(firstName, lastName));
	}

	@GetMapping("/getByLastNameAndFirstName") //?full=xxxx&last=xxx
	public StudentResponse getByFirstNameAndLastName(@RequestParam("first") String firstName, @RequestParam("last") String lastName) {
		return new StudentResponse(service.getByFullName(firstName, lastName));
	}
	@GetMapping("/getByState/{state}")
	public List<StudentResponse> getByState(@PathVariable("state") String state) {
		List<Student> students = service.getByState(state);
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		students.stream().forEach(student -> listresponse.add(new StudentResponse(student)));
		return listresponse;
				
	}
	@GetMapping("/getByStateJPQL/{state}")
	public List<StudentResponse> getByStateJPQL(@PathVariable("state") String state) {
		List<Student> students = service.getByStateJPQL(state);
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		students.stream().forEach(student -> listresponse.add(new StudentResponse(student)));
		return listresponse;
				
	}
	@GetMapping("/getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByFirstNameOrLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		List<Student> students = service.getByFirstNameOrLastName(firstName, lastName);
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		students.stream().forEach(student -> listresponse.add(new StudentResponse(student)));
		return listresponse;
				
	}
	@GetMapping("/getByFirstNameIn")
	public List<StudentResponse> getByFirstNameIn(@RequestBody InBodyRequest inNames) {
		List<Student> students = service.getByFirstNameIn(inNames.getInNames());
		List<StudentResponse> listresponse = new ArrayList<StudentResponse>();
		students.stream().forEach(student -> listresponse.add(new StudentResponse(student)));
		return listresponse;
				
	}
}
