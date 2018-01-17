package cn.sh.yhk.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sh.yhk.commone.AdminYhkBaseController;

@Controller
@RequestMapping("")
public class HomeController extends AdminYhkBaseController {

	protected static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/gologin")
	public String gologin() {
		return "login";
	}

	@RequestMapping("/weclome")
	public String gomain() {
		return "main";
	}
}