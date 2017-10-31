package com.tuberculosis.repository.data;

import com.tuberculosis.entity.data.LiverFunc;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LiverFuncDao extends PagingAndSortingRepository<LiverFunc, Long>,JpaSpecificationExecutor<LiverFunc> {

    LiverFunc findByPtInfoIdAndMxId(long ptInfoId, long mxId);

    @Modifying
    @Query(value="delete from  LiverFunc m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  LiverFunc m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
