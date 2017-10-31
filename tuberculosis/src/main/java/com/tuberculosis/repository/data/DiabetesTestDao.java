package com.tuberculosis.repository.data;

import com.tuberculosis.entity.data.DiabetesTest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DiabetesTestDao extends PagingAndSortingRepository<DiabetesTest, Long>,JpaSpecificationExecutor<DiabetesTest> {

    DiabetesTest findByPtInfoIdAndMxId(long ptInfoId, long mxId);

    @Modifying
    @Query(value="delete from  DiabetesTest m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  DiabetesTest m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
