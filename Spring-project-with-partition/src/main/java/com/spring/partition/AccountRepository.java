package com.spring.partition;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> { 
	Optional<Account> findByaccountId(Long accountId);
}
