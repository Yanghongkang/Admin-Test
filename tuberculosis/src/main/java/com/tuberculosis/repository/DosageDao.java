package com.tuberculosis.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tuberculosis.entity.Dosage;

public interface DosageDao extends PagingAndSortingRepository<Dosage, Long>, JpaSpecificationExecutor<Dosage> {

}
