package utcn.labs.sd.bankingservice.domain.data.converter;

import java.util.ArrayList;
import java.util.List;

import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Bill;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.BillDTO;

public class BillConverter {
	
	private BillConverter() {
		
	}
	public static BillDTO toDto(Bill model) {
		BillDTO dto = null;
		
		if(model != null) {
			dto = new BillDTO(model.getBillId(), model.getDescription(), model.getAmount(), model.getOfAccount(), model.getStatus());
		}
		return dto;
	}
	
	 public static List<BillDTO> toDto(List<Bill> models) {
	        List<BillDTO> billDtos = new ArrayList<>();
	        if ((models != null) && (!models.isEmpty())) {
	            for (Bill model : models) {
	                billDtos.add(toDto(model));
	            }
	        }
	        return billDtos;
	    }

}
