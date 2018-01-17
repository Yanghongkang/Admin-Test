package cn.sh.yhk.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.sh.yhk.admin.model.AdminRole;

@Mapper
public interface AdminRoleMapper {
	@Select("SELECT r.* FROM t_admin_role r LEFT JOIN t_admin_user_role t ON r.id = t.role_id LEFT JOIN t_admin_role u ON u.id = t.role_id WHERE t.user_id = #{id}")
	public AdminRole queryAdminRoleByUserId(long id);

}
