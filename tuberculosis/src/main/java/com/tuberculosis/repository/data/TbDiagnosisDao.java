package com.tuberculosis.repository.data;

import com.tuberculosis.entity.data.TbDiagnosis;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TbDiagnosisDao extends PagingAndSortingRepository<TbDiagnosis, Long>,JpaSpecificationExecutor<TbDiagnosis> {

//    @Query(value="SELECT t FROM t_tb_diagnosis t where t.pt_info_id = ?1 order by date desc limit 1")
//    public TbDiagnosis getLatestTbDiagnosis(Long ptInfoId);

    public TbDiagnosis findTop1ByPtInfoIdOrderByDateDesc(long ptInfoId);
    public List<TbDiagnosis> findByPtInfoIdOrderByDateDesc(long ptInfoId);

    TbDiagnosis findByPtInfoIdAndMxId(long ptInfoId, long mxId);

    @Modifying
    @Query(value="delete from  TbDiagnosis m  where m.mxId=?1 and  m.ptInfoId = ?2")
    public int deleteMxData(long mxId, long ptInfoId);

    @Modifying
    @Query(value="delete from  TbDiagnosis m  where  m.ptInfoId = ?1")
    public int deleteMxDataPtInfo(long ptInfoId);
}
