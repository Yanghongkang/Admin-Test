package cn.sh.yhk.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.sh.yhk.admin.model.AdminResource;

@Mapper
public interface AdminResourceMapper {
	@Insert("insert into t_admin_resource (name,type,level,url,status,create_date) values(#{name},#{type},#{level},#{url},#{status},#{createDate})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int saveAdminResource(AdminResource adminResource);

	@Select("select * from  t_admin_resource where id = #{id}")
	public AdminResource queryAdminResourceById(Long id);

	@Select("select * from  t_admin_resource where parent_id = #{parentid}")
	public List<AdminResource> queryAdminResourceByParentId(Long parentid);

	@Select("SELECT r.* FROM t_admin_resource r LEFT JOIN t_admin_role_resource t ON r.id = t.resource_id LEFT JOIN t_admin_role u ON u.id = t.role_id WHERE t.role_id = #{roleId} group by r.id ")
	@Results({ @Result(column = "parent_id", property = "parentId") })
	public List<AdminResource> queryAdminResourceByRoleId(long roleId);

}
