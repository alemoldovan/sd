package utcn.labs.sd.bankingservice.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.BillStatusType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDTO {

	private int billId;
	private String description;
	private float amount;
	private int ofAccount;
	private String status; 
	
	 @JsonCreator
    public BillDTO(@JsonProperty("billId") int billId,
                     @JsonProperty("description") String description,
                     @JsonProperty("amount") float amount,
                     @JsonProperty("ofAccount") int ofAccount,
                     @JsonProperty("status") String status) {
        this.billId = billId;
        this.description = description;
        this.amount = amount;
        this.ofAccount = ofAccount;
        this.status  = status;

        
        
    }

	 @JsonProperty("billId")
	public int getBillId() {
		return billId;
	}

	 @JsonProperty("description")
	public String getDescription() {
		return description;
	}

	 @JsonProperty("amount")
	public float getAmount() {
		return amount;
	}
	 
	 @JsonProperty("ofAccount")
	public int getOfAccount() {
		return ofAccount;
	}
	 
	 @JsonProperty("status")
	public String getStatus() {
		return status;
	}
	 
	 
	 @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BillDto{");
        sb.append("billId='").append(billId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", amount='").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
