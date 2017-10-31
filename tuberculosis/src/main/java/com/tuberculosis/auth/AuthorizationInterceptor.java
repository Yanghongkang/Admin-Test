package com.tuberculosis.auth;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.json.ValidatorError;
import com.tuberculosis.exception.AuthorizationException;
import com.tuberculosis.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li ShaoQing
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    /*----------------- example ---------
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="com.yjdgis.interceptors.MyInterceptor1"></bean>
        </mvc:interceptor>

　　  <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="com.yjdgis.interceptors.MyInterceptor2"></bean>
         </mvc:interceptor>
    </mvc:interceptors>
    ---------------------------------*/

    @Autowired
    AuthorizationTokenValidate authorizationTokenValidate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("Authentication");
        if(!(handler instanceof HandlerMethod)) return true; // not HandlerMethod return. eg. js css or other request url

        HandlerMethod handler2=(HandlerMethod) handler;
        Authentication authentication = handler2.getMethodAnnotation(Authentication.class);

        if(null == authentication){
            //没有声明权限,放行
            return true;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            this.authError(response);
            return false;
        }

        String token = null;

        for(Cookie cookie:cookies) {
            if(CommonContants.AUTH_TOKEN.equals(cookie.getName())) token = cookie.getValue();
        }
        if (token == null)  {
            this.authError(response);
            return false;
        }

        try{
            authorizationTokenValidate.validate(token);
        } catch (AuthorizationException e){
            logger.error("auth token validate error: ", e);
            this.authError(response);
            return false;
        }

        return true;
    }

    private void authError(HttpServletResponse response) {

        try {
            String json = JsonUtil.objectToJson(new ValidatorError(CommonContants.ERROR_CODE_TOKEN));
            response.getWriter().write(json);
        } catch (IOException e) {
            logger.error("authError: ", e);
        }

    }


}
