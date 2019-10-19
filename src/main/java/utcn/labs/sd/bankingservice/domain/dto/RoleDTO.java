package utcn.labs.sd.bankingservice.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.User;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.RoleType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {

	
	private int roleId;
    private RoleType roleType;
    private User user;
    

    @JsonCreator
    public RoleDTO(@JsonProperty("roleId") int roleId,
                     @JsonProperty("roleType") RoleType roleType,
                     @JsonProperty("user") User user) {
        this.roleId = roleId;
        this.roleType = roleType;
        this.user = user;
       
        
    }

    @JsonProperty("roleId")
	public int getRoleId() {
		return roleId;
	}


    @JsonProperty("roleType")
	public RoleType getRoleType() {
		return roleType;
	}

    @JsonProperty("user")
	public User getUser() {
		return user;
	}
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientDto{");
        sb.append("roleId='").append(roleId).append('\'');
        sb.append(", roleType='").append(roleType).append('\'');
        sb.append(", user='").append(user);
        sb.append('}');
        return sb.toString();
    }
    
    
}
