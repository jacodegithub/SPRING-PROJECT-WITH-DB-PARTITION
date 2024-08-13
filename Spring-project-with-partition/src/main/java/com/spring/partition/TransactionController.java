package com.spring.partition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions/")
public class TransactionController {

	
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private AccountRepository accRepo;

    @PostMapping("/credit")
    public String creditTransaction(@RequestBody TransactionRequest txn) {
    	transactionService.updateTransactionData(txn.getAccountId(), "deposit", txn.getDeposit());
    	return "Amount credited: ";
//      return  null;
    }
    @PostMapping("/debit")
    public String debitTransaction(@RequestBody TransactionRequest txn) {
    	transactionService.updateTransactionData(txn.getAccountId(), "withdraw", txn.getWithdraw());
    	return "Amount debited: ";
//      return  null;
    }
    

    @PostMapping("/acc-create")
    public Account createAccount(@RequestBody Account account) {
    	return accRepo.save(account);
    }
    
    @GetMapping("/ledger")
    public List<Transactions> getLedgerByAccountId(@RequestParam Long accountId) {
        return transactionService.getLedgerByAccountId(accountId);
    }
}
