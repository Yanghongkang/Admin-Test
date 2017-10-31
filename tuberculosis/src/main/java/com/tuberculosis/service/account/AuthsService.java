package com.tuberculosis.service.account;

import com.tuberculosis.entity.Auths;
import com.tuberculosis.repository.AuthsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限管理类.
 * 
 * @author Lishaoqing
 */
@Component
@Transactional(readOnly = true)
public class AuthsService {

    private static Logger logger = LoggerFactory.getLogger(AuthsService.class);

    private AuthsDao authsDao;

    public List<Auths> searchAuths() {
        return authsDao.getAuthsList();
    }

    @Autowired
    public void setAuthsDao(AuthsDao authsDao) {
        this.authsDao = authsDao;
    }

}
