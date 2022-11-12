package com.bookstore.repository;

import com.bookstore.entity.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from role where role_name=?1 and role_flag=0", nativeQuery = true)
    Role findRoleByRoleName(String name);
}

