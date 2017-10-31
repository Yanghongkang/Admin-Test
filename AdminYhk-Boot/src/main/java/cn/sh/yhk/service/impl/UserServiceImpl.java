package cn.sh.yhk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sh.yhk.dao.UserDao;
import cn.sh.yhk.model.User;
import cn.sh.yhk.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;

	@Override
	public List<User> FindAllUser() {
		// TODO Auto-generated method stub
		return userdao.findAll();
	}

}
