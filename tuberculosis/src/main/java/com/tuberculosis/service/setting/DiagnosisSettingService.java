package com.tuberculosis.service.setting;

import com.tuberculosis.entity.DiagnosisSetting;
import com.tuberculosis.exception.AuthorizationException;
import com.tuberculosis.repository.DiagnosisSettingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Lishaoqing
 */
@Component
@Transactional(readOnly = true)
public class DiagnosisSettingService {

    private static Logger logger = LoggerFactory.getLogger(DiagnosisSettingService.class);

    @Autowired
    private DiagnosisSettingDao diagnosisSettingDao;

    @Transactional(readOnly = false)
    public void save(DiagnosisSetting setting) throws AuthorizationException {
        diagnosisSettingDao.save(setting);
    }

    public DiagnosisSetting getSettingByType(int type) throws AuthorizationException {
        return diagnosisSettingDao.findDiagnosisSettingByDiagnosisType(type);
    }

    public DiagnosisSetting getSettingById(Long id) throws AuthorizationException {
        return diagnosisSettingDao.findOne(id);
    }

    public List<DiagnosisSetting> getAllSetting() throws AuthorizationException {
        return (List<DiagnosisSetting>) diagnosisSettingDao.findAll();
    }
}
