package com.pt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pt.jpa.repo.UserRepository;
import com.pt.jpa.repo.domain.UserPO;
import com.pt.service.UserService;
import com.pt.vo.UserEntity;

@Service("userSerivce")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository dao;
	
	@Override
	public UserEntity save(UserEntity userEntity) throws Exception {
		
		UserPO userPO = dao.findByLoginname(userEntity.getLoginname());
		if(userPO != null&&(userEntity.getId()==null||userEntity.getId()!=userPO.getId())) {
			throw new Exception("用户名重复");
		}
		
		UserPO user = new UserPO();
		BeanUtils.copyProperties(userEntity, user);
		user = dao.save(user);
		
		BeanUtils.copyProperties(user, userEntity);
		return userEntity;
	}

	@Override
	public void delete(UserEntity user) throws Exception{
		dao.delete(user.getId());
	}

	@Override
	public List<UserEntity> query() throws Exception{
		List<UserEntity> userList= new ArrayList<UserEntity>();
		Iterable<UserPO> users = dao.findAll();
		if(users==null) {
			return userList;
		}
		for(UserPO user:users) {
			UserEntity userEntity = new UserEntity();
			BeanUtils.copyProperties(user, userEntity);
			userList.add(userEntity);
		}
		return userList;
	}

	@Override
	public UserEntity query(Long id) throws Exception{
		UserPO user = dao.findOne(id);
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		return userEntity;
	}

	@Override
	public List<UserEntity> query(Integer pageNo, Integer pageSize) throws Exception{
		List<UserEntity> userList= new ArrayList<UserEntity>();
		Iterable<UserPO> users = dao.findAll(new PageRequest(pageNo - 1, pageSize, null));
		if(users==null) {
			return userList;
		}
		for(UserPO user:users) {
			UserEntity userEntity = new UserEntity();
			BeanUtils.copyProperties(user, userEntity);
			userList.add(userEntity);
		}
		return userList;
	}


}
