package com.tuberculosis.auth;

import com.tuberculosis.entity.User;
import com.tuberculosis.exception.AccountNotExistException;
import com.tuberculosis.exception.AuthorizationException;
import com.tuberculosis.exception.TokenExpiredException;
import com.tuberculosis.exception.TokenFormatInValidException;
import com.tuberculosis.service.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Li ShaoQing
 */
@Component
public class AuthorizationTokenValidate {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationTokenValidate.class);

    @Autowired
    private AccountService accountService;

    public User validate(String token) throws AuthorizationException {

        if(!AuthTokenGenerate.isTokenFormatValid(token)){
            logger.error("token format valid token is " + token);
            throw new TokenFormatInValidException("token format valid");
        }

        User user = accountService.findUserByToken(token);
        if(user == null) {
            logger.warn("account not exist. search by token:" + token);
            throw new AccountNotExistException("account not exist");
        }

        Date currentDate = new Date();
        if(currentDate.after(user.getExpireDate())) {
            logger.warn("token expired. token:" + token);
            throw new TokenExpiredException("token expired");
        }

        return user;
    }
}
