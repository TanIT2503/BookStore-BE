package com.bookstore.service;

import com.bookstore.dto.CustomerAccount;
import com.bookstore.entity.account.Account;
import com.bookstore.entity.customer.Customer;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Account findAccountByUsername(String username);
    Account findById(Long id);
    String getEncodedPassword(String password);
    void createCustomerAccount(CustomerAccount customerAccount);
}
