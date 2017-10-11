package com.cooksys.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cooksys.entity.Customer;
import com.cooksys.entity.Itinerary;

@Repository
public class CustomerRepository2 {

	private EntityManager entityManager;
	@Autowired
	public CustomerRepository2(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
//	@Transactional
//	public void updateHistory(Itinerary itinerary, Customer customer)
//	{
//		itinerary.setCustomer(customer);
//		entityManager.persist(itinerary);
//		entityManager.flush();
//		customer.getHistory().add(itinerary);
//		entityManager.persist(customer);
//		System.out.println(customer.getId() + "'s new history size " + customer.getHistory().size());
//	}
}
