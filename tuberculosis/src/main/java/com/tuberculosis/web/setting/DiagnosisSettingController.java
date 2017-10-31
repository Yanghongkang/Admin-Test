package com.tuberculosis.web.setting;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.DiagnosisSetting;
import com.tuberculosis.service.setting.DiagnosisSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/diagnosisSetting")
public class DiagnosisSettingController {

    @Autowired
    private DiagnosisSettingService diagnosisSettingService;


    @RequestMapping(value = "fei", method = RequestMethod.GET)
    public String fei(Model model) {
        model.addAttribute("diagnosisSetting", diagnosisSettingService.getSettingByType(CommonContants.DIAGNOSIS_TYPE_FEI));
        model.addAttribute("diagnosisType", CommonContants.DIAGNOSIS_TYPE_FEI);
        model.addAttribute("diagnosisName", "非结核");
        model.addAttribute("diagnosisTab", "fei");

        return "setting/ndySettingForm";
    }

    @RequestMapping(value = "putongchu", method = RequestMethod.GET)
    public String putongchu(Model model) {
        model.addAttribute("diagnosisSetting", diagnosisSettingService.getSettingByType(CommonContants.DIAGNOSIS_TYPE_PUTONG_CHU));
        model.addAttribute("diagnosisType", CommonContants.DIAGNOSIS_TYPE_PUTONG_CHU);
        model.addAttribute("diagnosisName", "普通结核初治");
        model.addAttribute("diagnosisTab", "putongchu");

        return "setting/ndySettingForm";
    }

    @RequestMapping(value = "putongfu", method = RequestMethod.GET)
    public String putongfu(Model model) {
        model.addAttribute("diagnosisSetting", diagnosisSettingService.getSettingByType(CommonContants.DIAGNOSIS_TYPE_PUTONG_FU));
        model.addAttribute("diagnosisType", CommonContants.DIAGNOSIS_TYPE_PUTONG_FU);
        model.addAttribute("diagnosisName", "普通结核复治");
        model.addAttribute("diagnosisTab", "putongfu");

        return "setting/ndySettingForm";
    }

    @RequestMapping(value = "danny", method = RequestMethod.GET)
    public String danny(Model model) {
        model.addAttribute("diagnosisSetting", diagnosisSettingService.getSettingByType(CommonContants.DIAGNOSIS_TYPE_DAN_NY));
        model.addAttribute("diagnosisType", CommonContants.DIAGNOSIS_TYPE_DAN_NY);
        model.addAttribute("diagnosisName", "单耐药结核");
        model.addAttribute("diagnosisTab", "danny");

        return "setting/ndySettingForm";
    }

    @RequestMapping(value = "duony", method = RequestMethod.GET)
    public String duony(Model model) {
        model.addAttribute("diagnosisSetting", diagnosisSettingService.getSettingByType(CommonContants.DIAGNOSIS_TYPE_DUO_NY));
        model.addAttribute("diagnosisType", CommonContants.DIAGNOSIS_TYPE_DUO_NY);
        model.addAttribute("diagnosisName", "多耐药结核");
        model.addAttribute("diagnosisTab", "duony");

        return "setting/ndySettingForm";
    }

    @RequestMapping(value = "naiduoyao", method = RequestMethod.GET)
    public String naiduoyao(Model model) {
        model.addAttribute("diagnosisSetting", diagnosisSettingService.getSettingByType(CommonContants.DIAGNOSIS_TYPE_NAIDUOYAO));
        model.addAttribute("diagnosisType", CommonContants.DIAGNOSIS_TYPE_NAIDUOYAO);
        model.addAttribute("diagnosisName", "耐多药结核");
        model.addAttribute("diagnosisTab", "naiduoyao");
        return "setting/ndySettingForm";
    }

    @RequestMapping(value = "guangfanny", method = RequestMethod.GET)
    public String guangfanny(Model model) {
        model.addAttribute("diagnosisSetting", diagnosisSettingService.getSettingByType(CommonContants.DIAGNOSIS_TYPE_GUANGFAN_NY));
        model.addAttribute("diagnosisType", CommonContants.DIAGNOSIS_TYPE_GUANGFAN_NY);
        model.addAttribute("diagnosisName", "广泛耐药结核");
        model.addAttribute("diagnosisTab", "guangfanny");

        return "setting/ndySettingForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateNaiduoyao(@ModelAttribute("preloadSetting") DiagnosisSetting setting, RedirectAttributes redirectAttributes) {

        diagnosisSettingService.save(setting);
        redirectAttributes.addFlashAttribute("message", "保存成功");

        int type = setting.getDiagnosisType();
        String url = "fei";
        switch (type) {
            case CommonContants.DIAGNOSIS_TYPE_FEI:
                url = "fei";
                break;
            case CommonContants.DIAGNOSIS_TYPE_PUTONG_CHU:
                url = "putongchu";
                break;
            case CommonContants.DIAGNOSIS_TYPE_PUTONG_FU:
                url = "putongfu";
                break;
            case CommonContants.DIAGNOSIS_TYPE_DAN_NY:
                url = "danny";
                break;
            case CommonContants.DIAGNOSIS_TYPE_DUO_NY:
                url = "duony";
                break;
            case CommonContants.DIAGNOSIS_TYPE_NAIDUOYAO:
                url = "naiduoyao";
                break;
            case CommonContants.DIAGNOSIS_TYPE_GUANGFAN_NY:
                url = "guangfanny";
                break;
        }
        return "redirect:/diagnosisSetting/"+url;
    }

    @ModelAttribute("preloadDiagnosisSetting")
    public DiagnosisSetting getDiagnosisSetting(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            return diagnosisSettingService.getSettingById(id);
        }
        return null;
    }
}
