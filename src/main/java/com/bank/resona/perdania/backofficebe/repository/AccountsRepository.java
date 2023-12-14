package com.bank.resona.perdania.backofficebe.repository;

import com.bank.resona.perdania.backofficebe.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    @Query(value = "SELECT * FROM accounts WHERE username=:username", nativeQuery = true)
    Optional<Accounts> getAccountsByUsername(@Param("username")String username);

}
