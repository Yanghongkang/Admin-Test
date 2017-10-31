package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName: Sendmail
 * @Description: 发送Email
 * 
 */
public class MailUtil {
	String mail;
	String activateid;
	static Properties prop = new Properties();

	public MailUtil() {
		// this.mail = mail;
		// this.activateid = activateid;
		prop.setProperty("mail.host", "smtp.126.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("proxySet", "true");
		prop.setProperty("ProxyHost", "192.162.30.9");
		prop.setProperty("ProxyPort", "808");
	}

	/*
	 * @Override public void run() { try { MailUtil.sendmail(mail, activateid);
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void sendmail(String mail, String activateid) throws Exception {
		System.out.println("===================" + mail);
		// 使用JavaMail发送邮件的5个步骤
		// 1、创建session
		Session session = Session.getInstance(prop);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		// 2、通过session得到transport对象
		Transport ts = session.getTransport();
		// 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		ts.connect("smtp.126.com", "blbq0724@126.com", "yhk19940520");
		// 4、创建邮件
		Message message = createSimpleMail(session, mail, activateid);
		// 5、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();

	}

	/**
	 * @Method: createSimpleMail
	 * @Description: 创建一封只包含文本的邮件
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createSimpleMail(Session session, String email, String id) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("blbq0724@126.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		message.setSubject("激活AdminYhk邮件");
		message.setContent(new PropertiesUtil().getproperties("resouce.properties", "actUserUrl") + "?actid" + id,
				"text/html;charset=UTF-8");
		return message;
	}

	public String getMail() {
		return mail;
	}

	public String getActivateid() {
		return activateid;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setActivateid(String activateid) {
		this.activateid = activateid;
	}

}