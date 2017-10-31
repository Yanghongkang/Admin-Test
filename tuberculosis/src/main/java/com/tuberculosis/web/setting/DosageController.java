package com.tuberculosis.web.setting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuberculosis.entity.Dosage;
import com.tuberculosis.entity.DosageVal;
import com.tuberculosis.entity.Hospital;
import com.tuberculosis.service.setting.DiagnosisSettingService;
import com.tuberculosis.service.setting.DosageService;

@Controller
@RequestMapping(value = "/dosageSetting")
public class DosageController {

	@Autowired
	private DosageService dosageService;

	@RequestMapping(value = "jiliang", method = RequestMethod.GET)
	public String jiliang(Model model,ServletRequest request) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		List<Dosage> dosages = dosageService.searchDosage(searchParams);
		for (Dosage dosage : dosages) {
			StringBuffer str  = new StringBuffer();
			List<DosageVal> beanList = mapper.readValue(dosage.getValue(), new TypeReference<List<DosageVal>>() {});
			for (DosageVal dosageVal : beanList) {
				str.append(dosageVal.getMin()+"kg~"+dosageVal.getMax()+"kg/"+dosageVal.getValue()+"mg</br>");
			}
			dosage.setValue(str.toString());
		}
		 
		model.addAttribute("dosages", dosages);
		return "setting/dosageList";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addjiliang(Model model) {
		return "setting/dosageForm";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(Model model,@PathVariable("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Dosage dos = dosageService.getDosage(id);
		
		List<DosageVal> beanList = mapper.readValue( dos.getValue(), new TypeReference<List<DosageVal>>() {});  
		model.addAttribute("dosage", dosageService.getDosage(id));
		model.addAttribute("dosagevalList",  beanList);
		return "setting/dosageForm";
	}
	
	@RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		Dosage dosage = dosageService.getDosage(id);
        dosageService.deleteDosage(id);

        redirectAttributes.addFlashAttribute("message", "删除剂量提醒" + dosage.getName() + "成功");
        return "redirect:/dosageSetting/jiliang";
    }

	@RequestMapping(value = "savedosage", method = RequestMethod.POST)
	public String savejiliang(Model model, RedirectAttributes redirectAttributes,String id, String name, Integer[] val,
			String[] mg) {
		Dosage dosage = new Dosage();
		ObjectMapper mapper = new ObjectMapper();
		dosage.setName(name);
		List<DosageVal> list = new ArrayList<>();
		for (int i = 0; i < mg.length; i++) {
			DosageVal dv = new DosageVal();
			if (i == mg.length-1) {
				dv.setMin("MAX");
				dv.setMax("MAX");
				dv.setValue(val[i]);
				list.add(dv);
			} else {
				String[] mgs = mg[i].split("~");
				dv.setMin(mgs[0]);
				dv.setMax(mgs[1]);
				dv.setValue(val[i]);
				list.add(dv);
			}
		}
		try {
			String str = mapper.writeValueAsString(list).replace("\\", "");
			dosage.setValue(str);
			if(id!=null&&id!=""){
				dosage.setId(Long.valueOf(id));
				dosage.setCraetedate(dosageService.getDosage(Long.valueOf(id)).getCraetedate());
				redirectAttributes.addFlashAttribute("message", "修改剂量标准"+name+"成功");
			}else{
				dosage.setCraetedate(new Date());
				redirectAttributes.addFlashAttribute("message", "保存剂量标准"+name+"成功");
			}
			dosageService.saveHospital(dosage);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "redirect:/dosageSetting/jiliang";
	}
}
