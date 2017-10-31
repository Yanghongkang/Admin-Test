package com.tuberculosis.repository;

import com.tuberculosis.entity.Auths;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 *
 * @author: Li Shaoqing
 */
public interface AuthsDao extends PagingAndSortingRepository<Auths,Long>,JpaSpecificationExecutor<Auths> {

    @Query(value="select auths from Auths auths order by auths.id asc")
    List<Auths> getAuthsList();
}
