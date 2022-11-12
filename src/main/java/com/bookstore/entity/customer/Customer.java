package com.bookstore.entity.customer;

import com.bookstore.entity.account.Account;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    private String customerPhone;
    @Column(unique = true)
    private String customerEmail;
    private String customerAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_account_id")
    private Account customerAccountId;
    private Boolean customerFlag = false;

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Account getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Account customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public Boolean getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(Boolean customerFlag) {
        this.customerFlag = customerFlag;
    }
}



