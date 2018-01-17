package cn.sh.yhk.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sh.yhk.admin.mapper.AdminResourceMapper;
import cn.sh.yhk.admin.mapper.AdminRoleMapper;
import cn.sh.yhk.admin.mapper.AdminUserMapper;
import cn.sh.yhk.admin.model.AdminResource;
import cn.sh.yhk.admin.model.AdminUser;
import cn.sh.yhk.admin.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	protected static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

	@Autowired
	AdminUserMapper adminUserMapper;
	@Autowired
	AdminRoleMapper adminRoleMapper;
	@Autowired
	AdminResourceMapper adminResourceMapper;

	@Override
	public List<AdminResource> getMeanByUser(AdminUser user) {
		List<AdminResource> ret = new ArrayList<AdminResource>();
		List<AdminResource> rets = adminResourceMapper.queryAdminResourceByRoleId(user.getRole().getId());
		for (AdminResource adminResource : ret) {
			if (adminResource.getParentId() == 0) {
				ret.add(adminResource);
				for (AdminResource childadminResource : ret) {
					if (adminResource.getId() == childadminResource.getParentId()) {
					}
				}
			}
		}
		return null;
	}

}
