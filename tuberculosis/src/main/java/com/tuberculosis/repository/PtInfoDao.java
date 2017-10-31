package com.tuberculosis.repository;

import com.tuberculosis.entity.PtInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface PtInfoDao extends PagingAndSortingRepository<PtInfo, Long>,JpaSpecificationExecutor<PtInfo> {

    List<PtInfo> findByIdCode(String idCode);
    List<PtInfo> findByHospitalNo(String hospitalNo);

    List<PtInfo> findByIdIn(Collection<Long> ptInfoIds);

    @Query(value="SELECT t FROM PtInfo t  order by t.id asc")
    List<PtInfo> findAllPtInfo();

}
