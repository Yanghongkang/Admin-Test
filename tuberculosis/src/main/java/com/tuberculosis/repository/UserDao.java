package com.tuberculosis.repository;

import com.tuberculosis.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User> {
	User findByName(String name);

    User findUserByLoginName(String loginName);

    User findUserByToken(String token);
}
