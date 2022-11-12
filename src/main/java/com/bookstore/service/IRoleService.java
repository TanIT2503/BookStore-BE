package com.bookstore.service;

import com.bookstore.entity.account.Role;

import java.util.List;

public interface IRoleService {
    Role findRoleByRoleName(String name);
    List<Role> findAllRole();
}
