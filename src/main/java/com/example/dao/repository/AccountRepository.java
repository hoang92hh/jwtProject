package com.example.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUsername(String username);
	Account findByEmail(String email);
	Account findByPhone(String phone);
	
	// JPQL
//    @Query("SELECT u FROM Account u WHERE u.username =:username")
//    Account getAccountBỵPQL(@Param("username") String username);

    // Native SQL
    //    @Query(value = "SELECT * FROM Users u WHERE u.status = :status and u.name = :name", nativeQuery = true)
    //    Account getAccountNativeSQL(@Param("status") Integer status, @Param("name") String name);
}
