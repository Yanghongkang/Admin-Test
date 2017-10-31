package com.tuberculosis.repository;

import com.tuberculosis.entity.DiagnosisSetting;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DiagnosisSettingDao extends PagingAndSortingRepository<DiagnosisSetting, Long>,JpaSpecificationExecutor<DiagnosisSetting> {

    DiagnosisSetting findDiagnosisSettingByDiagnosisType(int diagnosisType);
}
