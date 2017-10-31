package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author Yhk 2015年2月3日 上午10:02:19
 */
public class PropertiesUtil {
	private static Logger log = Logger.getLogger(PropertiesUtil.class);

	public static void main(String[] args) {
		System.out.println(new PropertiesUtil().getproperties("resouce.properties", "actUserUrl"));
	}

	/**
	 * 获取配置文件信息
	 * 
	 * @param name
	 *            键名
	 * @return
	 */
	public String getproperties(String propertiesname, String keyname) {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesname);
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			log.info("读取配置文件异常" + e.getMessage());
			e.printStackTrace();
		}
		return p.getProperty(keyname);
	}
}
