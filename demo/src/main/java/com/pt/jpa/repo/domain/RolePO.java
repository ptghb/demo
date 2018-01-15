package com.pt.jpa.repo.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RolePO extends BasePO {
	
	@ManyToMany(mappedBy="rolePOs")
    private Set<UserPO> userPOs;

	public Set<UserPO> getUserPOs() {
		return userPOs;
	}

	public void setUserPOs(Set<UserPO> userPOs) {
		this.userPOs = userPOs;
	}
}
