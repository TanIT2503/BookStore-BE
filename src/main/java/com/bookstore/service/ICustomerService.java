package com.bookstore.service;

import com.bookstore.entity.customer.Customer;

public interface ICustomerService {
    Customer findCustomerByAccountId(Long id);
}
