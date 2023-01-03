package com.mybanksystem.transaction;

import com.mybanksystem.transaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.accountFrom.UUID = :accountUUID or t.accountTo.UUID = :accountUUID")
    Collection<Transaction> findAllForAccount(@Param("accountUUID")String accountUUID);

    Transaction findTransactionByUUID(String transactionUUID);
}
