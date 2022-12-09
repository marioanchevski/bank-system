package com.mybanksystem.bank.repository;

import com.mybanksystem.bank.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBankRepository extends JpaRepository<Bank, Long> {
}
