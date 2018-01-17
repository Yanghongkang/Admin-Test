
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sh.yhk.Application;
import cn.sh.yhk.admin.model.AdminRole;
import cn.sh.yhk.admin.model.AdminUser;
import cn.sh.yhk.admin.service.ResourceService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class) // 指定spring-boot的启动类
public class AppTest {

	@Autowired
	ResourceService asdasdas;

	@Test
	public void findAllUsers() {
		AdminUser aaa1 = new AdminUser();
		aaa1.setId(1l);
		List<AdminRole> aa = new ArrayList<AdminRole>();
		AdminRole r1 = new AdminRole(1l);
		aa.add(r1);
		aaa1.setRole(r1);
		asdasdas.getMeanByUser(aaa1);
	}

}
