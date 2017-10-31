package com.tuberculosis.service.setting;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Dosage;
import com.tuberculosis.entity.Hospital;
import com.tuberculosis.exception.AuthorizationException;
import com.tuberculosis.repository.DosageDao;

@Component
@Transactional(readOnly = true)
public class DosageService {
	 private static Logger logger = LoggerFactory.getLogger(DosageService.class);

	    @Autowired
	    private DosageDao dosageDao;
	    
	    @Transactional(readOnly = false)
	    public void saveHospital(Dosage dosage) throws AuthorizationException {
	    	dosageDao.save(dosage);
	    }
	    
	    public List<Dosage> searchDosage (Map<String, Object> searchParams) {
	        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
	        Specification<Dosage> spec = DynamicSpecifications.bySearchFilter(filters.values(), Dosage.class);
	        Sort sort = new Sort(Sort.Direction.DESC, CommonContants.FIELD_ID);
	        return dosageDao.findAll(spec, sort);
	    }
	    
	    public List<Dosage> allDosage () {
	        return (List<Dosage>) dosageDao.findAll();
	    }

	    
	    public Dosage getDosage(Long id) throws AuthorizationException {
	        return dosageDao.findOne(id);
	    }

	    @Transactional(readOnly = false)
	    public void deleteDosage(Long id) {
	    	dosageDao.delete(id);
	    }
}
