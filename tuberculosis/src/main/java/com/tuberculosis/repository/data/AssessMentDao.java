package com.tuberculosis.repository.data;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tuberculosis.entity.data.Assessment;
import com.tuberculosis.entity.data.BiochemicalDetection;

public interface AssessMentDao extends PagingAndSortingRepository<Assessment, Long>,JpaSpecificationExecutor<Assessment> {
	
	Assessment findAssessMentDaoByPtInfoIdAndMxId(long ptInfoId, long mxId);

    @Modifying
    @Query(value="delete from  Assessment m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  Assessment m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
