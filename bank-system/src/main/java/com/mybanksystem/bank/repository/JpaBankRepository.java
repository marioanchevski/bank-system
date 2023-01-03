package com.mybanksystem.bank.repository;

import com.mybanksystem.bank.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findBankByName(String name);
    @Query("SELECT b.name FROM Bank b where b.id = :id")
    String findNameById(@Param("id") Long id);

    @Query("SELECT b.name FROM Bank b where b.UUID = :id")
    Optional<String> findNameByUUID(@Param("id") String id);

    Optional<Bank> findBankByUUID(String UUID);
}
