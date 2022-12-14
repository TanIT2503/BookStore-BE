package com.bookstore.dto;

import com.bookstore.entity.account.Account;
import com.bookstore.entity.customer.Customer;

public class CustomerAccount {
    private Customer customer;
    private Account account;

    public CustomerAccount() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
