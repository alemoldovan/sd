package utcn.labs.sd.bankingservice.domain.service;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.BillConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.ClientConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Bill;
import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.BillStatusType;
import utcn.labs.sd.bankingservice.domain.data.repository.AccountRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.BillRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.BillDTO;
import utcn.labs.sd.bankingservice.domain.dto.ClientDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private BillRepository billRepository;

    public List<AccountDTO> getAllAccounts() {
    	System.out.println("two");
    	List<Account> accounts = accountRepository.findAll();
    	System.out.println(accounts);
        return AccountConverter.toDto(accountRepository.findAll());
    }

    public AccountDTO getAccountById(Integer accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) throw new NotFoundException("No account found with that accountId");
        return AccountConverter.toDto(account);
    }
    
    public List<BillDTO> getBillsByAccounts(Integer accountId) throws Exception {
    	Account account =  accountRepository.findById(accountId).orElse(null);
        List<Bill> bills = account.getBillList();
        if (bills == null) throw new NotFoundException("No bills found for that accountId");
        return BillConverter.toDto(bills);
    }

    public AccountDTO createAccount(AccountDTO accountDto) throws Exception {
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        if (accountDto.getBalance() < 0) {
            throw new Exception("Impossible to have a negative balance");
        }
        System.out.println("1");
        Account account = new Account(accountDto.getAccountId(), accountDto.getAccountType(), currentTimestamp.toString(), accountDto.getBalance());
        Account newAccount = accountRepository.save(account);
        System.out.println("2");
        return AccountConverter.toDto(newAccount);
    }


    public AccountDTO updateAccount(Integer accountId, AccountDTO accountDto) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account found with that id");
        }
        
        account.setAccountType(accountDto.getAccountType());
        account.setCreationDate(accountDto.getCreationDate());
        account.setBalance(accountDto.getBalance());
        return AccountConverter.toDto(accountRepository.save(account));
    }


    public void deleteAccount(Integer accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account with that accountId");
        }
        accountRepository.delete(account);
    }
    
    
    public AccountDTO transferMoney(Integer accountId1, Integer accountId2, float amount)  throws Exception{
    	
    	Account account1 = accountRepository.findById(accountId1).orElse(null);
    	Account account2 = accountRepository.findById(accountId2).orElse(null);
    	
    	if (account1 == null) {
            throw new NotFoundException("The first account was not found");

        }
    	if (account2 == null) {
            throw new NotFoundException("The second account was not found");
        }
    	if(amount>0) {
	    	account1.setBalance(account1.getBalance() - amount);
	    	account2.setBalance(account2.getBalance() + amount);
    	}else {
    		throw new Exception("Can not transfer a negative amount of money");
    	}
    	
    	accountRepository.save(account2);
    	return AccountConverter.toDto(accountRepository.save(account1));
    	
    	
    }
    //trebuie facute validari, care se fac cu annotations
    
    public AccountDTO addBillToAccount(Integer accountId, BillDTO billDto) {
    	
    	System.out.println(accountId);
    	Account account = accountRepository.findById(accountId).orElse(null);
    	System.out.println(account);
        Bill bill = new Bill(billDto.getBillId(), billDto.getDescription(), billDto.getAmount(), billDto.getOfAccount(), billDto.getStatus());
        System.out.println(bill);
        account.getBillList().add(bill);
        System.out.println("HERE");
        billRepository.save(bill);
        System.out.println("HERE1");
        return AccountConverter.toDto(account);
    }

    
    public AccountDTO deleteBillFromAccount(Integer accountId, Integer billId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        billRepository.deleteById(billId);
        return AccountConverter.toDto(account);
    }
    
    public AccountDTO payBill(Integer accountId, Integer billId) throws Exception {
    	System.out.println("service paybill1");
    	Account account = accountRepository.findById(accountId).orElse(null);
    	System.out.println("service paybill2");
    	Bill bill = billRepository.findById(billId).orElse(null);
    	System.out.println("service paybill3");
    	List<Bill> bills = account.getBillList();
    	System.out.println("service paybill4");
    	System.out.println(bill.getStatus());
    	System.out.println(bills);
    	System.out.println(bills.contains(bill));
    	if(bills.contains(bill) && bill.getStatus().equals("UNPAID")) {
    		System.out.println("service paybill5");
    		account.setBalance(account.getBalance() - bill.getAmount());
    		System.out.println("service paybill6");
    		bill.setStatus("PAID");
    		System.out.println("service paybill7");
    	
    	
    	}else {
    		throw new Exception("Bill does not belong to the account with accountId!");
    	}
    		return AccountConverter.toDto(account);
    	
    }
    

}
