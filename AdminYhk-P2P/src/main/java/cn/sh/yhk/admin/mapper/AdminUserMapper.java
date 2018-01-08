package cn.sh.yhk.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.sh.yhk.admin.model.AdminUser;

@Mapper
public interface AdminUserMapper {
	@Select("select * from  t_admin_user where user_name = #{UserName}")
	public AdminUser getAdminUserByName(String UserName);

	@Select("select * from  t_admin_user where id = #{id}")
	@Results({
		 @Result(id=true,property="id",column="id"),
		 @Result(property="userName",column="user_name")
	})
	public AdminUser getAdminUserById(Long id);
}
