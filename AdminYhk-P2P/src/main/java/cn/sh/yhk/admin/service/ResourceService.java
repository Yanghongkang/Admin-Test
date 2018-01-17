package cn.sh.yhk.admin.service;

import java.util.List;

import cn.sh.yhk.admin.model.AdminResource;
import cn.sh.yhk.admin.model.AdminUser;

public interface ResourceService {
	public static String RESOURCE_TYPE_MENU = "menu";
	public static String RESOURCE_TYPE_BUTTON = "button";

	List<AdminResource> getMeanByUser(AdminUser user);
}
