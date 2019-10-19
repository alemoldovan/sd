package utcn.labs.sd.bankingservice.domain.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "client_table")
public class Client {
    @Id
    @NotNull(message = "SSN cannot be null")
    @Size(min=13, max=13, message = "SSN must have length 13")
    @Column(name = "SSN")
    private String ssn;

    @NotNull(message = "Firstname can not be null")
    @Column(name = "firstname")
    private String firstname;

    @NotNull(message = "lastname can not be null")
    @Column(name = "lastname")
    private String lastname;

    @NotNull(message = "identityCardNumber can not be null")
   // @Size(min=5, max=5, message = "identityCardNumber must have length 5")
    @Column(name = "identity_card_number")
    private String identityCardNumber;

    @NotNull(message = "address can not be null")
    @Column(name = "address")
    private String address;

    @NotNull(message = "email can not be null")
    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_ssn")
    private List<Account> accountList;


    public Client() {

    }

    public Client(String ssn, String firstname, String lastname, String identityCardNumber, String address, String email, List<Account> accountList) {
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
        this.email = email;
        this.accountList = accountList;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("ssn='").append(ssn).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", identityCardNumber='").append(identityCardNumber).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", accountList=").append(accountList);
        sb.append('}');
        return sb.toString();
    }
}
