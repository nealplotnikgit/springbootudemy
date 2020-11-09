package com.demo.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{//combo of CRUDRepository and PagingAndSortingRepository

	//get these by typing findBy the ctrl-space
	List<Student> findByFirstName(String firstName);
	
	//assumption here for 1 result. findBy, ctrl-space, type And, then ctrl-space again
	//not sure it didnt work in STS4. also, this should really return a list
	//the order of variables here will matter
	Student findByFirstNameAndLastName(String firstName, String lastName);
	List<Student> findByFirstNameOrLastName(String firstName, String lastName);
	List<Student> findByFirstNameIn(List<String> in);
	List<Student> findByAddressState(String state);  //find by the student's address's state field
	
	
	//jpql no need to specify select clause. use the entity class and it will pull the revelant columns. you canuse the @param if you want 
	@Query("From Student where lastName = :lastName and firstName = :first_name")
	Student getByLastNameAndFirstName(String lastName, @Param("first_name") String firstName);

	@Modifying
	@Transactional
	//2 return types - void or integer --> the count of updated records
	@Query("Update Student set firstName = :firstName where id = :id")
	Integer updateFirstName(Long id, String firstName);

	@Modifying
	@Transactional
	//2 return types - void or integer --> the count of deleted records
	@Query("Delete Student where firstName = :firstName")
	Integer deleteFirstName(String firstName);

	@Query("From Student where address.state = :state")
	List<Student> getByState(String state);

	
}

