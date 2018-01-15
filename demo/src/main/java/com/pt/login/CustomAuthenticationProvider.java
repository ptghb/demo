package com.pt.login;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.pt.jpa.repo.UserRepository;
import com.pt.jpa.repo.domain.RolePO;
import com.pt.jpa.repo.domain.UserPO;

//自定义身份认证验证组件
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository dao;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 获取认证的用户名 & 密码
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		// 通过用户名查找用户
		UserPO userPO = dao.findByLoginname(name);
		
		if(userPO == null) {
			throw new BadCredentialsException("user is not found!!!");
		}
		
		// 认证逻辑
		if (userPO.getPwd().equals(password)) {

			Set<RolePO> rolePOs = userPO.getRolePOs();
			// 这里设置权限和角色
			ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if(rolePOs!=null&&!rolePOs.isEmpty()) {

				for(RolePO rolePO:rolePOs) {
					GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(rolePO.getName());
					authorities.add(grantedAuthority);
				}
				
			}
			// 生成令牌
			Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
			return auth;

		} else {
			throw new BadCredentialsException("password is wrong!!!");
		}
	}

	// 是否可以提供输入类型的认证服务
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}