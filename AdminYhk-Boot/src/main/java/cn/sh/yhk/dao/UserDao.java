package cn.sh.yhk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.sh.yhk.model.User;

@Mapper
public interface UserDao {
	List<User> findAll();

	User findByName(@Param("cityName") String name);

}
