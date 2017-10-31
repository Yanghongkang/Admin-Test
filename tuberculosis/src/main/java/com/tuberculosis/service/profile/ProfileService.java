package com.tuberculosis.service.profile;

import com.tuberculosis.auth.AuthTokenGenerate;
import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Setting;
import com.tuberculosis.entity.User;
import com.tuberculosis.exception.*;
import com.tuberculosis.repository.SettingDao;
import com.tuberculosis.repository.UserDao;
import com.tuberculosis.service.account.AccountService;
import com.tuberculosis.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import java.util.Date;

/**
 *
 * @author Lishaoqing
 */
@Component
@Transactional(readOnly = true)
public class ProfileService {

    private static Logger logger = LoggerFactory.getLogger(ProfileService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private SettingDao settingDao;

    @Autowired
    private AccountService accountService;

    @Transactional(readOnly = false)
    public User login(User user) throws AuthorizationException {

        User loginUser = userDao.findUserByLoginName(user.getLoginName());
        if(loginUser == null) {
            logger.warn("account not exist:" + user.getLoginName());
            throw new AccountNotExistException("account not exist");
        }
        if(!this.checkPassword(user.getPlainPassword(), loginUser)) {
            logger.warn("wrong password. account is " + user.getLoginName());
            throw new WrongPasswordException("wrong password");
        }

        loginUser.setToken(AuthTokenGenerate.generateToken(String.valueOf(loginUser.getId())));
        loginUser.setExpireDate(DateUtil.hourOffset(new Date(), CommonContants.TOKEN_EXPIRE_DATE));
        userDao.save(loginUser);
        return loginUser;
    }

    @Transactional(readOnly = false)
    public void logout(String token) throws AuthorizationException {

        User loginUser = userDao.findUserByToken(token);
        if(loginUser == null) {
            logger.warn("account not exist. token : " + token);
            throw new AccountNotExistException("account not exist");
        }

        loginUser.setExpireDate(new Date());
        userDao.save(loginUser);
    }

    @Transactional(readOnly = false)
    public void modifyPassword(String token, String newPassword) throws AuthorizationException{

        if(StringUtils.isBlank(token)) {
            logger.warn("modify password error: token is empty");
            throw new TokenEmptyException("modify password error: token is empty");
        }

        User loginUser = userDao.findUserByToken(token);
        if(loginUser == null) {
            logger.warn("modify password error: account not exist, token is " + token);
            throw new AccountNotExistException("modify password error: account not exist");
        }

        if(CommonContants.IS_FIRST_LOGIN != loginUser.getFirstLogin()) {
            logger.warn("modify password error: can't modify password not first login, token is " + token);
            throw new PasswordChangeException("modify password error: can't modify password not first login");
        }

        loginUser.setPlainPassword(newPassword);
        accountService.entryptPassword(loginUser);
        loginUser.setFirstLogin(CommonContants.NOT_FIRST_LOGIN);
        userDao.save(loginUser);
    }

    public Setting adminInfo(long id) throws SettingNotFoundException {

        Setting setting = settingDao.findOne(id);
        if(setting == null) {
            logger.warn("admin setting not found. id is " + id);
            throw new SettingNotFoundException("admin setting not found");
        }

        return setting;
    }

    private boolean checkPassword(String plainPassword, User loginUser) {
        String checkPassword = Encodes.encodeHex(Digests.sha1(plainPassword.getBytes(), Encodes.decodeHex(loginUser.getSalt()), CommonContants.HASH_INTERATIONS));
        return loginUser.getPassword().equals(checkPassword);
    }
}
