package utcn.labs.sd.bankingservice.domain.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.BillConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Bill;
import utcn.labs.sd.bankingservice.domain.data.repository.BillRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.BillDTO;

@Service
public class BillService {
	
	@Autowired
	private BillRepository billRepository;
	
	 public List<BillDTO> getAllBills() {
		 	System.out.println("billgetall");
	        return BillConverter.toDto(billRepository.findAll());
	    }

	    public BillDTO getBillById(Integer billId) throws Exception {
	    	System.out.println("getbill " +billId);
	        Bill bill = billRepository.findById(billId).orElse(null);
	        System.out.println("bill: "+ bill);
	        if (bill == null) throw new NotFoundException("No bill found with that billId");
	        return BillConverter.toDto(bill);
	    }
	    
	 

	    public BillDTO createBill(BillDTO billDto) throws Exception {
	        
	        Bill bill = new Bill(billDto.getBillId(), billDto.getDescription(), billDto.getAmount(), billDto.getOfAccount(), billDto.getStatus());
	        Bill newBill = billRepository.save(bill);
	        return BillConverter.toDto(newBill);
	    }


	    public BillDTO updateBill(Integer billId, BillDTO billDto) throws Exception {
	        Bill bill = billRepository.findById(billId).orElse(null);
	        if (bill == null) {
	            throw new NotFoundException("No bill found with that id");
	        }
	        bill.setAmount(billDto.getAmount());
	        bill.setDescription(billDto.getDescription());
	        bill.setStatus(billDto.getStatus());
	        return BillConverter.toDto(billRepository.save(bill));
	    }


	    public void deleteBill(Integer billId) throws Exception {
	        Bill bill = billRepository.findById(billId).orElse(null);
	        if (bill == null) {
	            throw new NotFoundException("No bill with that billId");
	        }
	        billRepository.delete(bill);
	    }

}
