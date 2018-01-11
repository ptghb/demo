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

	UserPO findByNameAndAge(String name, Integer age);

    @Query("from UserPO u where u.name=:name")
    UserPO findUser(@Param("name") String name);
}
