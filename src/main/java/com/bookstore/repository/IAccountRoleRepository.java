package com.bookstore.repository;

import com.bookstore.entity.account.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRoleRepository extends JpaRepository<AccountRole, Long> {
}
