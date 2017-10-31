package com.tuberculosis.web.account;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Role;
import com.tuberculosis.entity.User;
import com.tuberculosis.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/account/user")
public class UserController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		List<User> users = accountService.searchUser(searchParams);
        model.addAttribute("users", users);
		model.addAttribute("allStatus", CommonContants.allStatus);
		return "account/userList";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", accountService.getUser(id));
		model.addAttribute("allStatus", CommonContants.allStatus);
		model.addAttribute("allRoles", accountService.getAllRole());
		return "account/userForm";
	}

	/**
	 * 演示自行绑定表单中的checkBox roleList到对象中.
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadUser") User user,ServletRequest request,RedirectAttributes redirectAttributes) {

        String[] checkedRoleList = request.getParameterValues("roleList");
		// bind roleList
		user.getRoleList().clear();
        if(checkedRoleList != null && checkedRoleList.length > 0){
            for (String roleId : checkedRoleList) {
                Role role = new Role(Long.valueOf(roleId));
                user.getRoleList().add(role);
            }
        }
		accountService.saveUser(user);

		redirectAttributes.addFlashAttribute("message", "保存用户成功");
		return "redirect:/account/user";
	}

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        User user = accountService.getUser(id);
        accountService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
        return "redirect:/account/user";
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add() {
        return "account/userAddForm";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(@Valid User user,RedirectAttributes redirectAttributes) {

        user.setStatus(0);
        user.setRegisterDate(new Date());
        accountService.saveUser(user);

        redirectAttributes.addFlashAttribute("message", "新增用户"+user.getLoginName()+"成功");
        return "redirect:/account/user";
    }

    /**
     * Ajax请求校验loginName是否唯一。
     */
    @RequestMapping(value = "checkNewName")
    @ResponseBody
    public String checkLoginName(@RequestParam("loginName") String loginName) {
        if (accountService.findUserByLoginName(loginName) == null) {
            return "true";
        } else {
            return "false";
        }
    }

	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("oldLoginName") String oldLoginName,
			@RequestParam("loginName") String loginName) {
		if (loginName.equals(oldLoginName)) {
			return "true";
		} else if (accountService.findUserByLoginName(loginName) == null) {
			return "true";
		}

		return "false";
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preloadUser")
	public User getUser(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return accountService.getUser(id);
		}
		return null;
	}

	/**
	 * 不自动绑定对象中的roleList属性，另行处理。
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("roleList");
	}

}
