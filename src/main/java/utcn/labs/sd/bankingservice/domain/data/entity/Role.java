
package utcn.labs.sd.bankingservice.domain.data.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.RoleType;

@Entity
@Table(name="role_table")
public class Role {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roleid")
	private int roleId;
	
	 @Column(name = "role")
	 @Enumerated(EnumType.STRING)
	 @NotNull(message = "Role cannot be null")
	 private RoleType role;
	 

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Role(int roleId, RoleType role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	
	public Role() {
		
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}

	

	 
	 
	 
}
