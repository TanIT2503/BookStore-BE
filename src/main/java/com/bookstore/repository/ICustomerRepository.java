package com.bookstore.repository;

import com.bookstore.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
	@Query(value = "SELECT * FROM customer WHERE customer_account_id =?1", nativeQuery = true)
	Customer findCustomerByAccountId(Long accountId);
}
