package utcn.labs.sd.bankingservice.domain.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.BillStatusType;

@Entity
@Table(name="bill_table")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int billId;
	
	
	@Column(name = "description")
	@NotNull(message = "description can not be null")
	@Size(min = 1, max = 45, message = "Description length can not be bigger than 45")
	private String description;
	
	
	@Column(name = "amount")
	@NotNull(message = "amount can not be null")
	@PositiveOrZero(message = "The balance must be positive")
	private float amount;
	
	@Column(name = "of_account")
	private Integer ofAccount;

	
	@Column(name = "status")
	private String status;
	
	

	public int getBillId() {
		return billId;
	}


	public void setBillId(int billId) {
		this.billId = billId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}

	

	public Integer getOfAccount() {
		return ofAccount;
	}


	public void setOfAccount(Integer ofAccount) {
		this.ofAccount = ofAccount;
	}

	
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Bill(int billId, String description, float amount, Integer ofAccount, String status) {
		super();
		this.billId = billId;
		this.description = description;
		this.amount = amount;
		this.ofAccount = ofAccount;
		this.status = status;
	}

	public Bill() {
		
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", description=" + description + ", amount=" + amount + "]";
	}
	
	
	
	
	
	

}
