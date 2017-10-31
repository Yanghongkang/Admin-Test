package com.tuberculosis.web.rest;

import com.tuberculosis.auth.Authentication;
import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Role;
import com.tuberculosis.entity.Setting;
import com.tuberculosis.entity.User;
import com.tuberculosis.entity.json.AdminSettingResponse;
import com.tuberculosis.entity.json.UserResponse;
import com.tuberculosis.exception.AuthorizationException;
import com.tuberculosis.exception.SettingNotFoundException;
import com.tuberculosis.exception.validator.ValidatorErrors;
import com.tuberculosis.service.profile.ProfileService;
import com.tuberculosis.service.setting.DosageService;
import com.tuberculosis.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Li Shaoqing
 */
@Controller
@RequestMapping(value = "/rest/user")
public class ProfileAPIController {

	@Autowired
    private ProfileService profileService;
	
	@Autowired
    private DosageService dosageService;
	

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public UserResponse login(User user, HttpServletResponse response) {

        User loginUser;
        try {
            loginUser = profileService.login(user);
        } catch (AuthorizationException ex) {
            UserResponse userResponse = new UserResponse();
            userResponse.setErrorCode(CommonContants.ERROR_CODE_PWD_WRONG);
            return userResponse;
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setName(loginUser.getName());
        userResponse.setToken(loginUser.getToken());
        userResponse.setExpireDate(loginUser.getExpireDate());
        userResponse.setFirstLogin(loginUser.getFirstLogin());
        userResponse.setRecvInst(loginUser.getRecvInst());
		userResponse.setDoslist(dosageService.allDosage());
        

        List<Role> roles = loginUser.getRoleList();
        if(roles != null && roles.size() > 0) {
            StringBuilder permission = new StringBuilder();
            int size = roles.size();

            for(int i = 0; i < size; i++) {
                permission.append(roles.get(i).getPermissions());
                if(i < size - 1) permission.append(",");
            }
            userResponse.setPermission(permission.toString());
        }

        // TODO should delete
//        Cookie cookie = new Cookie(CommonContants.AUTH_TOKEN, loginUser.getToken());
//        cookie.setMaxAge(100000);
//        cookie.setPath("/");
//        cookie.setDomain(".gcwind.com");
//        response.addCookie(cookie);

        return userResponse;
    }

    @RequestMapping(value = "logout/{token}", method = RequestMethod.GET)
    @ResponseBody
    public UserResponse logout(@PathVariable(value="token") String token,
                         HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie:cookies) {
                Cookie removeCookie = new Cookie(cookie.getName(), cookie.getValue());
                removeCookie.setMaxAge(0);
                response.addCookie(removeCookie);
            }
        }

        UserResponse userResponse = new UserResponse();

        try {
            profileService.logout(token);
        } catch (AuthorizationException ex) {
            userResponse.setErrorCode(CommonContants.ERROR_CODE_LOGOUT_ERROR);
        }

        return userResponse;
    }

    @RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
    @Authentication
    @ResponseBody
    public UserResponse modifyPassword( @RequestParam(value="newPassword") String newPassword,
                                 @RequestParam(value="confirmPassword") String confirmPassword,
                                 HttpServletRequest request) {

        ValidatorErrors validatorErrors = new ValidatorErrors();
        ValidatorUtil.validatorPassword(newPassword, confirmPassword, validatorErrors);
        UserResponse userResponse = new UserResponse();

        if(validatorErrors.hasErrors()) {
            userResponse.setErrorCode(CommonContants.ERROR_CODE_CHANGE_PWD);
            return userResponse;
        }

        Cookie[] cookies = request.getCookies();
        String token = null;
        for(Cookie cookie:cookies) {
            if(CommonContants.AUTH_TOKEN.equals(cookie.getName())) token = cookie.getValue();
        }

        try {
            profileService.modifyPassword(token, newPassword);
        } catch (AuthorizationException ex) {
            userResponse.setErrorCode(CommonContants.ERROR_CODE_CHANGE_PWD);
            return userResponse;
        }

        return userResponse;
    }

    @RequestMapping(value = "adminInfo", method = RequestMethod.GET)
    @ResponseBody
    public AdminSettingResponse adminInfo() {

        AdminSettingResponse settingResponse = new AdminSettingResponse();

        try {
            Setting setting = profileService.adminInfo(CommonContants.SETTING_ID);
            settingResponse.setAdminName(setting.getAdminName());
            settingResponse.setMobile(setting.getMobile());
            settingResponse.setTelephone(setting.getTelephone());
            settingResponse.setAddress(setting.getAddress());
        } catch (SettingNotFoundException ex) {
            settingResponse.setErrorCode(CommonContants.ERROR_CODE_ADMIN_INFO);
        }

        return settingResponse;
    }

}
