package cn.sh.yhk.p2p.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sh.yhk.commone.AdminYhkBaseController;
import cn.sh.yhk.p2p.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController extends AdminYhkBaseController {

	protected static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/initUserList")
	public String gologin() {
		return "user/user_list";
	}

	@RequestMapping("/searchuserlist")
	public void searchuserlist(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
		writeToResponse(userService.searchUser(page-1, limit));
	}

	@RequestMapping("/initadduser")
	public String goaddUser() {
		return "user/user_add";
	}

}