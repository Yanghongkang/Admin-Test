/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.tuberculosis.repository;

import com.tuberculosis.entity.DiagnosisSetting;
import com.tuberculosis.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class SettingDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private DiagnosisSettingDao diagnosisSettingDao;

	@Test
	public void getDiagnosisSetting() throws Exception {
//        Map<String, Object> searchParams = new HashMap<>();
//        searchParams.put("EQ_m0", "dfdfdf");
//        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//        Specification<DiagnosisSetting> spec = DynamicSpecifications.bySearchFilter(filters.values(), DiagnosisSetting.class);
//
//        List<DiagnosisSetting> settings = (List<DiagnosisSetting>) diagnosisSettingDao.findAll(spec);
//
//        DiagnosisSetting s = diagnosisSettingDao.findDiagnosisSettingByDiagnosisType(344);
//        System.out.println(settings.size());
    }


}
