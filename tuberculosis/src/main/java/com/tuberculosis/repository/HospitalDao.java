package com.tuberculosis.repository;

import com.tuberculosis.entity.Hospital;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HospitalDao extends PagingAndSortingRepository<Hospital, Long>,JpaSpecificationExecutor<Hospital> {

}
