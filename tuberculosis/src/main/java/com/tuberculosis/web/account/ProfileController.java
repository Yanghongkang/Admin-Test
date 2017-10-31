package com.tuberculosis.web.account;

import com.tuberculosis.entity.User;
import com.tuberculosis.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author Li Shaoqing
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

	@Autowired
    private AccountService accountService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", accountService.getUser(id));
		return "account/profile";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadUser") User user) {
		accountService.updateUser(user);

		return "redirect:/";
	}

	@ModelAttribute("preloadUser")
	public User getUser(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return accountService.getUser(id);
		}
		return null;
	}

}
