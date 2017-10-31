package cn.sh.yhk.commone.plugins.sso;

import javax.servlet.http.HttpSession;
/**
 * 
 * @author AdminYHK
 *
 */
public class AdminYhkSsoObject {

	private HttpSession session;
	private String sessionId;
	private String unixTime;
	private String vCode;

	public AdminYhkSsoObject(HttpSession session, String unixTime, String vCode) {
		super();
		this.session = session;
		this.unixTime = unixTime;
		this.sessionId = session.getId();
		this.vCode = vCode;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUnixTime() {
		return unixTime;
	}

	public void setUnixTime(String unixTime) {
		this.unixTime = unixTime;
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

}
