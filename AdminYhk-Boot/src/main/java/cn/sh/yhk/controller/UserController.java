package cn.sh.yhk.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.sh.yhk.commone.AdminYhkBaseController;
import cn.sh.yhk.dao.redis.RedisService;
import cn.sh.yhk.service.UserService;

@Controller
public class UserController extends AdminYhkBaseController{
	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;

	@RequestMapping(value = "/all.do", method = RequestMethod.GET)
	public void allUser(HttpServletResponse response) {
		writeToResponse(userService.FindAllUser());
		
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(HttpServletResponse response) {
		return "login";
	}

	@RequestMapping(value = "/count.do", method = RequestMethod.GET)
	public void count(HttpServletResponse response) {
		writeToResponse(redisService.get("Server_Count_Num"));
		
	}
}
