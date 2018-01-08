
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sh.yhk.Application;
import cn.sh.yhk.admin.mapper.AdminUserMapper;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class) // 指定spring-boot的启动类
public class AppTest {
	@Autowired
	AdminUserMapper adminUserMapper;

	@Test
	public void findAllUsers() {
		System.out.println(adminUserMapper);
		System.out.println(adminUserMapper.getAdminUserById(1l).toString());
	}

}
