package com.tuberculosis.repository.data;

import com.tuberculosis.entity.data.Hain;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HainDao extends PagingAndSortingRepository<Hain, Long>,JpaSpecificationExecutor<Hain> {

    Hain findByPtInfoIdAndMxId(long ptInfoId, long mxId);

    @Query(value="SELECT t FROM Hain t  where t.ptInfoId=?1 order by t.date asc")
    List<Hain> findAllOrderByDateAsc(long ptInfoId);

    @Modifying
    @Query(value="delete from  Hain m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  Hain m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
