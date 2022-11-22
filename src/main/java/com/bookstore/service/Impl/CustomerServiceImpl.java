package com.bookstore.service.Impl;

import com.bookstore.entity.customer.Customer;
import com.bookstore.repository.ICustomerRepository;
import com.bookstore.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	ICustomerRepository customerRepository;

	@Override
	public Customer findCustomerByAccountId(Long accountId) {
		return customerRepository.findCustomerByAccountId(accountId);
	}

}
