package com.tuberculosis.repository;

import com.tuberculosis.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author: Li Shaoqing
 */
public interface RoleDao extends PagingAndSortingRepository<Role,Long>,JpaSpecificationExecutor<Role> {

    Role  findByName(String name);
}
