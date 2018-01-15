package com.pt.jpa.repo.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserPO extends BasePO {

	@Column
	private String loginname;

	@Column
    private String pwd;
    
    @JoinTable(name="user_role",
    	joinColumns={@JoinColumn(name="userid", referencedColumnName="id")},
    	inverseJoinColumns={@JoinColumn(name="roleid", referencedColumnName="id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RolePO> rolePOs;
    
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}	
    public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Set<RolePO> getRolePOs() {
		return rolePOs;
	}
	public void setRolePOs(Set<RolePO> rolePOs) {
		this.rolePOs = rolePOs;
	}

}
