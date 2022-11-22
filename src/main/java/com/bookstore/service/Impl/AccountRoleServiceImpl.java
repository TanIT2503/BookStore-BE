package com.bookstore.service.Impl;

import com.bookstore.dto.CustomerAccount;
import com.bookstore.entity.account.Account;
import com.bookstore.entity.account.Role;
import com.bookstore.repository.IAccountRepository;
import com.bookstore.repository.ICustomerRepository;
import com.bookstore.repository.IRoleRepository;
import com.bookstore.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountRoleServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void createCustomerAccount(CustomerAccount customerAccount) {
//        if (accountRepository.findAccountByUsername(customerAccount.getAccount().getUsername()) != null){
//            System.out.println("Username existed");
//        }else {
        Set<Role> roles = new HashSet<>();
        customerRepository.save(customerAccount.getCustomer());
        Optional<Role> role = Optional.of(roleRepository.findById(Long.valueOf(2)).get());
        roles.add(role.get());
        Account account = customerAccount.getAccount();
        account.setRoles(roles);
        account.setPassword(getEncodedPassword(customerAccount.getAccount().getPassword()));
        customerAccount.getCustomer().setCustomerAccountId(account);
        customerRepository.save(customerAccount.getCustomer());
//        }
    }
}
