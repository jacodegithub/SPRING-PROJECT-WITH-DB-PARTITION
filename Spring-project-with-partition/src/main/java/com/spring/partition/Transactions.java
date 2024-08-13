package com.spring.partition;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.Mapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name="transactions",
		indexes = {
				@Index(name = "idx_prevTransaction", columnList = "prev_transaction_id"),
				@Index(name = "idx_nextTransaction", columnList = "next_transaction_id")
		})
public class Transactions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
//	@Column(name ="user_id")
//	private Long userId;
    @ManyToOne
    private Account account;
    
//	private Long accountId;
	@Column(name ="deposit")
	private Double deposit;
	@Column(name ="withdraw")
	private Double withdraw;
	@Column(name ="transaction_date")
	private LocalDateTime transactionDate;
	
	@Column(name ="prev_transaction_id")
	private String previousTransactionId;
	@Column(name ="next_transaction_id")
	private String nextTransactionId;
	
	@Column(name ="running_balance")
	private Double runningBalance;
	
	public Transactions() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}

//	public Long getAccountId() {
//		return accountId;
//	}
//
//	public void setAccountId(Long accountId) {
//		this.accountId = accountId;
//	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Double withdraw) {
		this.withdraw = withdraw;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPreviousTransactionId() {
		return previousTransactionId;
	}

	public void setPreviousTransactionId(String previousTransactionId) {
		this.previousTransactionId = previousTransactionId;
	}

	public String getNextTransactionId() {
		return nextTransactionId;
	}

	public void setNextTransactionId(String nextTransactionId) {
		this.nextTransactionId = nextTransactionId;
	}

	public Double getRunningBalance() {
		return runningBalance;
	}

	public void setRunningBalance(Double runningBalance) {
		this.runningBalance = runningBalance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}