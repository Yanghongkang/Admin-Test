package commone;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量
 * 
 * @ClassName: CommonConstant
 * @Description: TODO
 * @author yanghongkang
 * @date 2015-11-21 下午5:35:42
 *
 */
public class CommonConstant {
	// url集合
	public static List<String> MVCURL = new ArrayList<String>();

	final public static String[] access = new String[] { "/home/index.do", "/home/register.do",
			"/home/registeruser.do" };

	// session中用户对象
	public static final String SESSION_USER = "SESSION_USER";
	
}
