package com.spring.partition;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

	Optional<Transactions> findFirstByAccountIdOrderByTransactionDateDesc(Long accountId);

	List<Transactions> findByAccountIdOrderByTransactionDateAsc(Long accountId);
}
