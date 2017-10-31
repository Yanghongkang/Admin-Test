package com.tuberculosis.web.setting;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Hospital;
import com.tuberculosis.entity.Role;
import com.tuberculosis.entity.User;
import com.tuberculosis.service.account.AccountService;
import com.tuberculosis.service.setting.SettingService;
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
@RequestMapping(value = "/setting/hospital")
public class HospitalController {

	@Autowired
	private SettingService settingService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		List<Hospital> hospitals = settingService.searchHospital(searchParams);
        model.addAttribute("hospitals", hospitals);
		return "setting/hospitalList";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("hospital", settingService.getHospital(id));
		return "setting/hospitalForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadHospital") Hospital hospital, RedirectAttributes redirectAttributes) {

        settingService.saveHospital(hospital);

		redirectAttributes.addFlashAttribute("message", "保存医院成功");
		return "redirect:/setting/hospital";
	}

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Hospital hospital = settingService.getHospital(id);
        settingService.deleteHospital(id);

        redirectAttributes.addFlashAttribute("message", "删除医院" + hospital.getName() + "成功");
        return "redirect:/setting/hospital";
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add() {
        return "setting/hospitalAddForm";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(@Valid Hospital hospital,RedirectAttributes redirectAttributes) {

        settingService.saveHospital(hospital);
        redirectAttributes.addFlashAttribute("message", "新增医院"+ hospital.getName()+"成功");
        return "redirect:/setting/hospital";
    }

	@ModelAttribute("preloadHospital")
	public Hospital getHospital(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return settingService.getHospital(id);
		}
		return null;
	}

}
