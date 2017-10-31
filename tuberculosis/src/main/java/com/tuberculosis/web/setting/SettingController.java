package com.tuberculosis.web.setting;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Setting;
import com.tuberculosis.service.setting.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

	@RequestMapping(value = "adminInfo", method = RequestMethod.GET)
	public String adminInfo(Model model) {
		model.addAttribute("setting", settingService.getSetting(CommonContants.SETTING_ID));
		return "setting/adminSettingForm";
	}

    @RequestMapping(value = "adminInfo", method = RequestMethod.POST)
    public String updateAdminInfo(@ModelAttribute("preloadSetting") Setting setting, RedirectAttributes redirectAttributes) {
        settingService.save(setting);
        redirectAttributes.addFlashAttribute("message", "保存成功");
        return "redirect:/setting/adminInfo";
    }

    @ModelAttribute("preloadSetting")
    public Setting getSetting(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            return settingService.getSetting(id);
        }
        return null;
    }
}
