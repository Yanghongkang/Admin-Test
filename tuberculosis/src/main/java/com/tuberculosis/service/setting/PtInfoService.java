package com.tuberculosis.service.setting;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.PtInfo;
import com.tuberculosis.exception.AuthorizationException;
import com.tuberculosis.repository.PtInfoDao;
import com.tuberculosis.service.data.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lishaoqing
 */
@Component
@Transactional(readOnly = true)
public class PtInfoService {

    private static Logger logger = LoggerFactory.getLogger(PtInfoService.class);

    @Autowired
    private PtInfoDao ptInfoDao;

    @Autowired
    private DataService dataService;

    @Transactional(readOnly = false)
    public void save(PtInfo ptInfo) throws AuthorizationException {
        ptInfoDao.save(ptInfo);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) throws AuthorizationException {

        dataService.deletePtInfo(id);
        ptInfoDao.delete(id);
    }

    @Transactional(readOnly = false)
    public void death(PtInfo ptInfo) throws AuthorizationException {
        PtInfo pt = ptInfoDao.findOne(ptInfo.getId());
        pt.setDeathDate(ptInfo.getDeathDate());
        pt.setDeathReason(ptInfo.getDeathReason());
        ptInfoDao.save(pt);
    }

    public PtInfo getPtInfo(Long id) throws AuthorizationException {
        return ptInfoDao.findOne(id);
    }

    public List<PtInfo> getPtInfoByIdCode(String idCode) {
        return ptInfoDao.findByIdCode(idCode);
    }

    public List<PtInfo> findByHospitalNo(String hospitalNo) {
        return ptInfoDao.findByHospitalNo(hospitalNo);
    }

    public Page<PtInfo> searchPtInfo(Map<String, Object> searchParams, int pageNumber, int pageSize) {

        Sort sort = new Sort(Sort.Direction.DESC, CommonContants.FIELD_ID);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<PtInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), PtInfo.class);
        return ptInfoDao.findAll(spec, pageRequest);
    }

    public List<PtInfo> findAll() {
        return ptInfoDao.findAllPtInfo();
    }

    public List<PtInfo> searchPtInfoInId(Collection<Long> ptInfoIds) {
        return ptInfoDao.findByIdIn(ptInfoIds);
    }
}
