package utcn.labs.sd.bankingservice.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import utcn.labs.sd.bankingservice.domain.data.entity.Bill;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private final int accountId;
    private final AccountType accountType;
    private final String creationDate;
    private final float balance;
    private List<Bill> billList;

    @JsonCreator
    public AccountDTO(@JsonProperty("accountId") int accountId,
                      @JsonProperty("accountType") AccountType accountType,
                      @JsonProperty("creationDate") String creationDate,
                      @JsonProperty("balance") float balance,
                      @JsonProperty("billList") List<Bill> billList
                      ) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.creationDate = creationDate;
        this.balance = balance;
        this.billList  = billList;
    }

    @JsonProperty("accountId")
    public int getAccountId() {
        return accountId;
    }

    @JsonProperty("accountType")
    public AccountType getAccountType() {
        return accountType;
    }

    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("balance")
    public float getBalance() {
        return balance;
    }

    
    @JsonProperty("billList")
    public List<Bill> getBillList() {
		return billList;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account ");
        sb.append(" with accountId=").append(accountId);
        sb.append(", accountType=").append(accountType);
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", balance=").append(balance).append('\'');
        sb.append(", billList=").append(billList);
        sb.append('}');
        return sb.toString();
    }
}
