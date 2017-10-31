package cn.sh.yhk.p2p.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.sh.yhk.commone.AdminYhkResponseTableData;
import cn.sh.yhk.p2p.dao.UserDao;
import cn.sh.yhk.p2p.model.User;

@Component
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserDao userdao;

	@SuppressWarnings("unchecked")
	public AdminYhkResponseTableData<User> searchUser(int page, int size) {
		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(page, size, sort);
		Page<User> puser = userdao.findAll(pageable);
		AdminYhkResponseTableData<User> users = new AdminYhkResponseTableData<User>();
		users.setData((List<User>) puser.getContent());
		users.setCount((int)puser.getTotalElements());
		return users;
	}

}
