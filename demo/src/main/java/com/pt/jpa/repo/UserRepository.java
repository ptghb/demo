package com.pt.jpa.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pt.jpa.repo.domain.UserPO;

/**
 * User持久化
 * @author gehb
 *
 */
public interface UserRepository extends BaseRepository<UserPO,Long>{

	/**
	 * 登录注册用
	 * @param loginname
	 * @return
	 */
	UserPO findByLoginname(String loginname);

    @Query("from UserPO u where u.loginname=:loginname")
    UserPO findUser(@Param("loginname") String loginname);
}
