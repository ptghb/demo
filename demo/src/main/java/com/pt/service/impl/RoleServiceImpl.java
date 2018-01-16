package com.pt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pt.jpa.repo.RoleRepository;
import com.pt.jpa.repo.domain.RolePO;
import com.pt.service.RoleService;
import com.pt.vo.RoleEntity;

@Service("roleSerivce")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository dao;
	
	@Override
	public RoleEntity save(RoleEntity roleEntity) throws Exception {
		
		RolePO role = new RolePO();
		BeanUtils.copyProperties(roleEntity, role);
		role = dao.save(role);
		
		BeanUtils.copyProperties(role, roleEntity);
		return roleEntity;
	}

	@Override
	public void delete(RoleEntity role) throws Exception{
		dao.delete(role.getId());
	}

	@Override
	public List<RoleEntity> query() throws Exception{
		List<RoleEntity> roleList= new ArrayList<RoleEntity>();
		Iterable<RolePO> roles = dao.findAll();
		if(roles==null) {
			return roleList;
		}
		for(RolePO role:roles) {
			RoleEntity roleEntity = new RoleEntity();
			BeanUtils.copyProperties(role, roleEntity);
			roleList.add(roleEntity);
		}
		return roleList;
	}

	@Override
	public RoleEntity query(Long id) throws Exception{
		RolePO role = dao.findOne(id);
		RoleEntity roleEntity = new RoleEntity();
		BeanUtils.copyProperties(role, roleEntity);
		return roleEntity;
	}

	@Override
	public List<RoleEntity> query(Integer pageNo, Integer pageSize) throws Exception{
		List<RoleEntity> roleList= new ArrayList<RoleEntity>();
		Iterable<RolePO> roles = dao.findAll(new PageRequest(pageNo - 1, pageSize, null));
		if(roles==null) {
			return roleList;
		}
		for(RolePO role:roles) {
			RoleEntity roleEntity = new RoleEntity();
			BeanUtils.copyProperties(role, roleEntity);
			roleList.add(roleEntity);
		}
		return roleList;
	}


}
