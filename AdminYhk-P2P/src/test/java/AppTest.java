

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import cn.sh.yhk.Application;
import cn.sh.yhk.p2p.dao.UserDao;
import cn.sh.yhk.p2p.model.User;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class) // 指定spring-boot的启动类
public class AppTest {
	@Autowired
	private UserDao userdao;

	@Test
	public void findAllUsers() {
		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(0, 5, sort);
		System.out.println(userdao.findByUserName("admin"));
	}

}
