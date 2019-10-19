package utcn.labs.sd.bankingservice.domain.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;

@Entity
@Table(name="user_table")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "iduser")
	private int userId;
	
	@ManyToOne()
	@JoinColumn(name = "roleid")
	private Role role;
	

	@Column(name = "username")
	@NotNull(message = "username can not be null")
    private String username;

    @Column(name = "password")
    @NotNull(message = "password can not be null")
    @Size(min = 6, max=10, message = "password must containt at least 6 characters and at most 10 characters")
    private String password;
    
    @Column(name = "name")
    @NotNull(message = "name can not be null")
    private String name;

    @Email
    @Column(name = "email")
    @NotNull(message = "email can not be null")
    private String email;



	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public User(int userId,  String username, String password, String name, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	

	public User() {
		
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", role=" + role + ", username=" + username + ", password=" + password
				+ ", name=" + name + ", email=" + email + "]";
	}

	
	
    
    
    


}
