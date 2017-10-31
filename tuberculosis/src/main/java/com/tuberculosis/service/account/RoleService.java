package com.tuberculosis.service.account;

import com.tuberculosis.entity.Role;
import com.tuberculosis.repository.RoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * 
 * @author 
 */
@Component
@Transactional(readOnly = true)
public class RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleService.class);
    private RoleDao roleDao;

    public List<Role> searchRole(Map<String, Object> searchParams) {

        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Role> spec = DynamicSpecifications.bySearchFilter(filters.values(), Role.class);
        return roleDao.findAll(spec);
    }

    @Transactional(readOnly = false)
    public void saveRole(Role role){
        roleDao.save(role);
    }

    @Transactional(readOnly = false)
    public void deleteRole(Long id) {
        roleDao.delete(id);
    }

    public Role findRoleByName(String name) {
        return roleDao.findByName(name);
    }

    public Role getRole(Long id){
        return roleDao.findOne(id);
    }

    public List<Role> getAllRole() {
        return (List<Role>) roleDao.findAll();
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
