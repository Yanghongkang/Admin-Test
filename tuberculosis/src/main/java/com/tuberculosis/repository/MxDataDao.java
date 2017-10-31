package com.tuberculosis.repository;

import com.tuberculosis.entity.MxData;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface MxDataDao extends PagingAndSortingRepository<MxData, Long>,JpaSpecificationExecutor<MxData> {

//    @Modifying
//    @Query(value="delete from  MxData m where m.ptInfoId = ?1 and m.date >= ?2 and m.type= ?3 and m.status = ?4")
//    public int delMxData(long ptInfoId, Date date, int type, int status);

    @Query(value="select m from MxData m where m.ptInfoId = ?1 and m.status = ?2 order by m.date asc, m.id asc")
    public List<MxData> mxDataList(long ptInfoId, int status);

//    @Query(value="select m from MxData m where m.ptInfoId = ?1 and m.type= ?2 and m.date >= ?3 and m.date <= ?4 order by m.date desc, m.id desc")
//    public List<MxData> searchMxDataByDate(long ptInfoId, int type, Date beginDate, Date endDate);

    @Query(value="select m from MxData m where  m.date <= ?1 order by m.ptInfoId asc, m.date asc")
    public List<MxData> searchVisitMxDataInDate(Date endDate);

    @Query(value="select m from MxData m where  m.ptInfoId = ?1  and m.date = ?2 and m.status = ?3")
    public List<MxData> checkMxData(long ptInfoId, Date date, int status);

    @Modifying
    @Query(value="update MxData m set m.status = ?1 where m.id=?2 and  m.ptInfoId = ?3")
    public int deleteLogic(int status, long id, long ptInfoId);

    @Modifying
    @Query(value="delete from  MxData m  where m.id=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long id, long ptInfoId);

    @Modifying
    @Query(value="delete from  MxData m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);

    MxData findTop1ByPtInfoIdOrderByDateDesc(long ptInfoId);

    @Modifying
    @Query(value="update MxData m set m.therapy = ?1, m.currTherapy = '' where m.id=?2")
    public int updateTherapy(int therapy, long id);

//    @Query(value="select m from MxData m order by m.date asc limit 1")
    MxData findTopByOrderByDateAsc();

//    @Query(value="select m from MxData m order by m.date desc limit 1")
    MxData findTopByOrderByDateDesc();
}
