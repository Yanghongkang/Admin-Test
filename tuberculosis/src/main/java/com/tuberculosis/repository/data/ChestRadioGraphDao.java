package com.tuberculosis.repository.data;

import com.tuberculosis.entity.data.ChestRadioGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface ChestRadioGraphDao extends PagingAndSortingRepository<ChestRadioGraph, Long>,JpaSpecificationExecutor<ChestRadioGraph> {

    ChestRadioGraph findByPtInfoIdAndMxId(long ptInfoId, long mxId);

    ChestRadioGraph findTop1ByPtInfoIdOrderByDateDesc(long ptInfoId);

    ChestRadioGraph findTop1ByPtInfoIdOrderByDateAsc(long ptInfoId);

    ChestRadioGraph findTop1ByPtInfoIdAndDateBeforeOrderByDateDesc(long ptInfoId, Date date);

    List<ChestRadioGraph> findTop2ByPtInfoIdOrderByDateDesc(long ptInfoId);

    @Modifying
    @Query(value="delete from  ChestRadioGraph m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  ChestRadioGraph m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
