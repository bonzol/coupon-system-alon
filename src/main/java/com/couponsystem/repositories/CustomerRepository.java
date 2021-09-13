package com.couponsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean findByEmail(String email);

	boolean existsByEmail(String email);

	boolean findByPassword(String password);

	boolean findByFirstName(String firstName);

	Customer findById(int id);

	Customer getById(int id);

	Customer getByEmail(String email);
}
