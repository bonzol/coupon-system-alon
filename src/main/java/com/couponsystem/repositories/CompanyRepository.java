package com.couponsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByName(String name);

	boolean findByName(String name);

	boolean findByPasswordIs(String password);

	Company findById(int id);

	Company getByName(String name);

	Company getByEmail(String email);

	Company getById(int id);

}
