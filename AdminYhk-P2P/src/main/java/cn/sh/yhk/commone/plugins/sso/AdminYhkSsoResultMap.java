package cn.sh.yhk.commone.plugins.sso;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.sh.yhk.p2p.model.User;
import cn.sh.yhk.util.DateUtil;

/**
 * 自建SSO缓存库
 * 
 * @author AdminYHK
 *
 */
@Component
public class AdminYhkSsoResultMap {
	protected static Logger logger = LoggerFactory.getLogger(AdminYhkSsoResultMap.class);

	final private static Map<String, AdminYhkSsoObject> loginUserMap = new HashMap<String, AdminYhkSsoObject>();

	public synchronized AdminYhkSsoObject getLoginUserMap(String key) {
		logger.info("SSO获取" + key);
		return loginUserMap.get(key);
	}

	public synchronized void setLoginUserMap(User user, HttpSession session, String Vcode) {
		String unixTime = DateUtil.formatDate7(new Date());
		String key = user.getUserNo() + user.getId() + user.getUserPwd().substring(10);
		AdminYhkSsoObject sso = new AdminYhkSsoObject(session, unixTime, Vcode);
		loginUserMap.put(key, sso);
		logger.info("SSO设置" + key + "成功");
	}

	public synchronized void removeLoginUserMap(String key) {
		logger.info(JSON.toJSONString(getLoginUserMap(key)));
		logger.info("SSO删除" + loginUserMap.remove(key));
	}

	public static void main(String[] args) {
		System.out.println("1234567890".substring(5));
	}
}
