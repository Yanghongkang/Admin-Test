package com.tuberculosis.web.account;

import com.tuberculosis.entity.Auths;
import com.tuberculosis.entity.Role;
import com.tuberculosis.service.account.AuthsService;
import com.tuberculosis.service.account.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.utils.Collections3;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/account/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

    @Autowired
    private AuthsService authsService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		List<Role> roles = roleService.searchRole(searchParams);
        model.addAttribute("roles", roles);
		return "account/roleList";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {

        Role role = roleService.getRole(id);
        List<Auths> auths = authsService.searchAuths();
        Map<String,Auths> authsMap = this.getAuthsMap(auths);
        List<String> permissonList = role.getPermissionList();

        for(String p:permissonList){
            Auths a = authsMap.get(p);
            if(a != null){
                role.getAuthList().add(a);
            }
        }
		model.addAttribute("role", role);
        model.addAttribute("allAuths", auths);
		return "account/roleForm";
	}

    /**
	 * 演示自行绑定表单中的checkBox roleList到对象中.
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadRole") Role role,ServletRequest request,RedirectAttributes redirectAttributes) {

        String[] checkedAuthList = request.getParameterValues("authList");

        if(checkedAuthList != null && checkedAuthList.length > 0){
            List list = Arrays.asList(checkedAuthList);
            String permissions = Collections3.convertToString(list, ",");

            if(permissions.contains("mng:")){
                permissions = permissions + ",mng:title";
            }

            role.setPermissions(permissions);
        }
		roleService.saveRole(role);

		redirectAttributes.addFlashAttribute("message", "保存角色成功");
		return "redirect:/account/role";
	}

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Role role = roleService.getRole(id);
        roleService.deleteRole(id);

        redirectAttributes.addFlashAttribute("message", "删除角色" + role.getName() + "成功");
        return "redirect:/account/role";
    }

   @RequestMapping(value = "add",method = RequestMethod.GET)
   public String add() {
       return "account/roleAddForm";
   }

   @RequestMapping(value = "add",method = RequestMethod.POST)
   public String add(@Valid Role role,RedirectAttributes redirectAttributes) {
       roleService.saveRole(role);

       redirectAttributes.addFlashAttribute("message", "新增角色"+role.getName()+"成功");
       return "redirect:/account/role";
   }

   /**
    * Ajax请求校验name是否唯一。
    */
   @RequestMapping(value = "checkNewRoleName")
   @ResponseBody
   public String checkNewRoleName(@RequestParam("name") String name) {
       if (roleService.findRoleByName(name) == null) {
           return "true";
       } else {
           return "false";
       }
   }

	@RequestMapping(value = "checkRoleName")
	@ResponseBody
	public String checkRoleName(@RequestParam("oldRoleName") String oldRoleName,
			@RequestParam("name") String name) {
		if (name.equals(oldRoleName)) {
			return "true";
		} else if (roleService.findRoleByName(name) == null) {
			return "true";
		}

		return "false";
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preloadRole")
	public Role getRole(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
            return roleService.getRole(id);
		}
		return null;
	}

    private Map<String, Auths> getAuthsMap(List<Auths> auths) {
        Map<String, Auths> map = new HashMap();
        for(Auths auth:auths) {
            map.put(auth.getPermission(),auth);
        }
        return map;
    }
}
