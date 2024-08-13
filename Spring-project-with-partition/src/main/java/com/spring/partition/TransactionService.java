package com.spring.partition;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository transRepo;
	
	@Autowired
	private AccountRepository accRepo;

	public Long updateTransactionData(Long accountId, String operation, double amount) {
		
		Account account = accRepo.findByaccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
		System.out.println("this is account ->"+account.getId());
		double lastBalance = 0.0;

		Transactions transaction = new Transactions();
		transaction.setAccount(account);
		
		Optional<Transactions> lastTransaction = transRepo.findFirstByAccountIdOrderByTransactionDateDesc(account.getId());
		System.out.println("this is last transaction ->"+transaction.getAccount().getAccountId());
		
		if (lastTransaction.isPresent()) {
			lastBalance = lastTransaction.get().getRunningBalance();
		}
		
		double newBalance = 0.0;
		if(operation.equals("deposit")) {
			transaction.setDeposit(amount);	
			newBalance = lastBalance + amount;
		}
		else  {
			transaction.setWithdraw(-amount);
			newBalance = lastBalance - amount;
		}
		
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setPreviousTransactionId(null);
		transaction.setNextTransactionId(null);

		transaction.setRunningBalance(newBalance);

		Transactions savedTrans = transRepo.save(transaction);

		if (lastTransaction.isPresent()) {
			Transactions prevTransaction = lastTransaction.get();
			prevTransaction.setNextTransactionId(String.valueOf(transaction.getId()));
			transaction.setPreviousTransactionId(String.valueOf(prevTransaction.getId()));
		}

		return 0l;
	}

	
	  public String getTransactionId() {
	  
		  String alpha_num = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		  SecureRandom secureRandom = new SecureRandom(); int length = 10;
		  StringBuilder sb = new StringBuilder();
		  
		  for(int i=0; i<length; ++i) { int randomIdx =
		  secureRandom.nextInt(alpha_num.length());
		  sb.append(alpha_num.charAt(randomIdx)); }
		  
		  return sb.toString(); 
	  }
	 
	  
	    public List<Transactions> getLedgerByAccountId(Long accountId) {
	        return transRepo.findByAccountIdOrderByTransactionDateAsc(accountId);
	    }

}
