package com.bookstore.controller;

import com.bookstore.entity.customer.Customer;
import com.bookstore.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/customer")
public class CustomerController {
	@Autowired
	ICustomerService customerService;

	@GetMapping("/getCustomerByAccount")
	public ResponseEntity<Customer> findCustomerByAccountId(@RequestParam("accountId") Long accountId) {
		Customer customer = this.customerService.findCustomerByAccountId(accountId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
}
