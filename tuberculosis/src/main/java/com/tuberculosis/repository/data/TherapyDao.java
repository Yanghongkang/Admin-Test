package com.tuberculosis.repository.data;

import com.tuberculosis.entity.data.Therapy;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface TherapyDao extends PagingAndSortingRepository<Therapy, Long>,JpaSpecificationExecutor<Therapy> {

    Therapy findByPtInfoIdAndMxId(long ptInfoId, long mxId);

//    @Query(value="SELECT t FROM Therapy t where t.ptInfoId=?1 and (t.therapyStatus =?2 or t.therapyStatus =?3) order by t.date desc limit 1")
    Therapy findTop1ByPtInfoIdAndTherapyStatusNotAndTherapyStatusNotOrderByDateDesc(long ptInfoId, int therapyStatus, int therapyStatusTwo);

    Therapy findTop1ByPtInfoIdAndTherapyStatusNotAndTherapyStatusNotAndDateBeforeOrderByDateDesc(long ptInfoId, int therapyStatus, int therapyStatusTwo, Date date);

    Therapy findTop1ByPtInfoIdAndTherapyStatusAndDateBeforeAndCurrTherapyNotNullOrderByDateDesc(long ptInfoId, int therapyStatus, Date date);

    @Modifying
    @Query(value="delete from  Therapy m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  Therapy m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
