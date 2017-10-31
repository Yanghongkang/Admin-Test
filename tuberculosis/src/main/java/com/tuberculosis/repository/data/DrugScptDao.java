package com.tuberculosis.repository.data;

import com.tuberculosis.entity.data.DrugScpt;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DrugScptDao extends PagingAndSortingRepository<DrugScpt, Long>,JpaSpecificationExecutor<DrugScpt> {

    DrugScpt findByPtInfoIdAndMxId(long ptInfoId, long mxId);

    @Query(value="SELECT t FROM DrugScpt t  where t.ptInfoId = ?1 order by t.date asc")
    List<DrugScpt> findAllOrderByDateAsc(Long ptInfoId);

    @Modifying
    @Query(value="delete from  DrugScpt m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  DrugScpt m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
