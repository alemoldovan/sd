package utcn.labs.sd.bankingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import utcn.labs.sd.bankingservice.domain.data.entity.Role;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	
	private int userId;
	private Role role;
	private String username;
	private String password;
	private String name;
	private String email;
	
	 @JsonCreator
    public UserDTO(@JsonProperty("userId") int userId,
    		@JsonProperty("role") Role role,
    		@JsonProperty("username") String username,
    		@JsonProperty("password") String password,
    		@JsonProperty("name") String name,
    		@JsonProperty("email") String email
    		) {
    	
    	this.userId = userId;
    	this.role = role;
    	this.username = username;
    	this.password = password;
    	this.name = name;
    	this.email = email;

    }

	 @JsonProperty("userId")
	public int getUserId() {
		return userId;
	}

	 @JsonProperty("role")
	public Role getRole() {
		return role;
	}

	 @JsonProperty("username")
	public String getUsername() {
		return username;
	}

	 @JsonProperty("password")
	public String getPassword() {
		return password;
	}

	 @JsonProperty("name")
	public String getName() {
		return name;
	}

	 @JsonProperty("email")
	public String getEmail() {
		return email;
	}
	 
	 
	

}
