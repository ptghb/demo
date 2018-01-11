package com.pt.jpa.repo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserPO extends BasePO {

    @Column(nullable = false)
    private Integer age;
    
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
   
    
}
