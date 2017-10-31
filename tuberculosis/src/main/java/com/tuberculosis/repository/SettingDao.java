package com.tuberculosis.repository;

import com.tuberculosis.entity.Setting;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SettingDao extends PagingAndSortingRepository<Setting, Long>,JpaSpecificationExecutor<Setting> {
}
