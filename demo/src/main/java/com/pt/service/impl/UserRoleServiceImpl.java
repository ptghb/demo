package com.pt.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.jpa.repo.RoleRepository;
import com.pt.jpa.repo.UserRepository;
import com.pt.jpa.repo.UserRoleRepository;
import com.pt.jpa.repo.domain.RolePO;
import com.pt.jpa.repo.domain.UserPO;
import com.pt.jpa.repo.domain.UserRolePO;
import com.pt.service.UserRoleService;
import com.pt.vo.UserRoleEntity;

@Service("userRoleSerivce")
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleRepository dao;
	@Autowired
	private UserRepository userdao;
	@Autowired
	private RoleRepository roledao;
	
	@Override
	public UserRoleEntity save(UserRoleEntity userRoleEntity) throws Exception {
		
		UserPO user = userdao.findOne(userRoleEntity.getUserid());
		if(user==null) {
			throw new Exception("不存在此用户");
		}
		RolePO role = roledao.findOne(userRoleEntity.getRoleid());
		if(role==null) {
			throw new Exception("不存在此权限");
		}
		
		UserRolePO userRole = dao.findByUseridAndRoleid(userRoleEntity.getUserid(), userRoleEntity.getRoleid());

		if(userRole==null) {
			userRole = new UserRolePO();
			userRole.setUserid(userRoleEntity.getUserid());
			userRole.setRoleid(userRoleEntity.getRoleid());
			userRole = dao.save(userRole);
		}

		userRoleEntity.setId(userRole.getId());
		return userRoleEntity;
	}

	@Override
	public void delete(UserRoleEntity userRoleEntity) throws Exception{
		UserRolePO userRole = dao.findByUseridAndRoleid(userRoleEntity.getUserid(), userRoleEntity.getRoleid());
		if(userRole==null) {
			return;
		}
		dao.delete(userRole.getId());
	}

	@Override
	public List<UserRoleEntity> query() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleEntity query(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRoleEntity> query(Integer pageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
