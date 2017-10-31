package com.tuberculosis.repository.data;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tuberculosis.entity.data.BiochemicalDetection;

public interface BiochemicalDetectionDao extends PagingAndSortingRepository<BiochemicalDetection, Long>,JpaSpecificationExecutor<BiochemicalDetection> {
	
	BiochemicalDetection findBiochemicalDetectionByPtInfoIdAndMxId(long ptInfoId, long mxId);

    @Modifying
    @Query(value="delete from  BiochemicalDetection m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  BiochemicalDetection m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
