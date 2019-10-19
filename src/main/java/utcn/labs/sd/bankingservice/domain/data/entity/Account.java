package utcn.labs.sd.bankingservice.domain.data.entity;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account_table")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "account_type")
    @NotNull(message = "Account type can not be null")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "date_of_creation")
    private String creationDate;

    @Column(name = "balance")
    @NotNull(message = "balance can not be null")
    @PositiveOrZero(message = "The balance must be positive")
    private float balance;
    
     @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "idaccount")
	 private List<Bill> billList;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    
    
    public List<Bill> getBillList() {
		return billList;
	}

	public void setBillList(List<Bill> billList) {
		this.billList = billList;
	}



  

	public Account(int accountId, AccountType accountType, String creationDate, float balance) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.creationDate = creationDate;
		this.balance = balance;
		
	}

	public Account() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("accountId=").append(accountId);
        sb.append(", accountType=").append(accountType);
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
