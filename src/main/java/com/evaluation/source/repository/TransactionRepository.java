package com.evaluation.source.repository;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evaluation.source.model.Transaction;
import com.evaluation.source.model.Transaction.Method;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // @Query("SELECT t FROM Transaction t WHERE (t.buyer.id = :userId OR t.seller.id = :userId) AND t.method = :method AND t.transaction_status = :transactionStatus ORDER BY t.created_at DESC")
    // Page<Transaction> findTransaction(
    //     Long userId,
    //     Method method, 
    //     TransactionStatus transactionStatus,
    //     Pageable pageable);
}
