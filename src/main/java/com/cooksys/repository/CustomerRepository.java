package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Customer findByCredentialsUsername(String username);
	public Customer findByCredentialsUsernameAndCredentialsPassword(String username, String password);
}
