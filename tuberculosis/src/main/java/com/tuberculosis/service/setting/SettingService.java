package com.tuberculosis.service.setting;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Hospital;
import com.tuberculosis.entity.Setting;
import com.tuberculosis.entity.User;
import com.tuberculosis.exception.AuthorizationException;
import com.tuberculosis.repository.HospitalDao;
import com.tuberculosis.repository.SettingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Lishaoqing
 */
@Component
@Transactional(readOnly = true)
public class SettingService {

    private static Logger logger = LoggerFactory.getLogger(SettingService.class);

    @Autowired
    private SettingDao settingDao;

    @Autowired
    private HospitalDao hospitalDao;

    @Transactional(readOnly = false)
    public void save(Setting setting) throws AuthorizationException {
        settingDao.save(setting);
    }

    public Setting getSetting(Long id) throws AuthorizationException {
        return settingDao.findOne(id);
    }


    public List<Hospital> searchHospital(Map<String, Object> searchParams) {

        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Hospital> spec = DynamicSpecifications.bySearchFilter(filters.values(), Hospital.class);
        Sort sort = new Sort(Sort.Direction.DESC, CommonContants.FIELD_ID);
        return hospitalDao.findAll(spec, sort);
    }

    @Transactional(readOnly = false)
    public void saveHospital(Hospital hospital) throws AuthorizationException {
        hospitalDao.save(hospital);
    }

    public Hospital getHospital(Long id) throws AuthorizationException {
        return hospitalDao.findOne(id);
    }

    @Transactional(readOnly = false)
    public void deleteHospital(Long id) {
        hospitalDao.delete(id);
    }
}
