package com.demo.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Address;
import com.example.demo.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{//combo of CRUDRepository and PagingAndSortingRepository


	
}

