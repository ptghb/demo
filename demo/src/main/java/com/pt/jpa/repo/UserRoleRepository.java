package com.pt.jpa.repo;

import com.pt.jpa.repo.domain.UserRolePO;

/**
 * UserRole持久化
 * @author gehb
 *
 */
public interface UserRoleRepository extends BaseRepository<UserRolePO,Long>{
	
	UserRolePO findByUseridAndRoleid(Long userid,Long roleid);
}
