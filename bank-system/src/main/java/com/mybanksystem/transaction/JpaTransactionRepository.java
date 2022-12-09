package com.mybanksystem.transaction;

import com.mybanksystem.transaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long> {
}
