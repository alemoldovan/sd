package utcn.labs.sd.bankingservice.domain.data.converter;


import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;


public class AccountConverter {

    private AccountConverter() {
    }

    public static AccountDTO toDto(Account model) {
        AccountDTO dto = null;
        if (model != null) {
            dto = new AccountDTO(model.getAccountId(), model.getAccountType(), model.getCreationDate(), model.getBalance(), model.getBillList());
        }
        return dto;
    }

    public static List<AccountDTO> toDto(List<Account> models) {
        List<AccountDTO> accountDtos = new ArrayList<>();
        if ((models != null) && (!models.isEmpty())) {
        	System.out.println("three");
            for (Account model : models) {
                accountDtos.add(toDto(model));
                System.out.println("model: " + model);
            }
        }
        return accountDtos;
    }
}
