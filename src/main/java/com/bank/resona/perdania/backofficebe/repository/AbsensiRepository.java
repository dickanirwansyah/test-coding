package com.bank.resona.perdania.backofficebe.repository;

import com.bank.resona.perdania.backofficebe.entity.Absensi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbsensiRepository extends JpaRepository<Absensi, Long> {

    @Query(value = "SELECT * FROM absensi WHERE accounts_id=:accounts", nativeQuery = true)
    Optional<Absensi> getAbsensiByAccounts(@Param("accounts")Long accounts);
}
