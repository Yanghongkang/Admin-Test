package commone;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 基类Controller
 * 
 * @ClassName: BaseController
 * @Description: TODO
 * @author yanghongkang
 * @date 2015-11-21 下午5:49:15
 *
 */
public class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	public void writeToResponse(Object obj) {
		try {

			response.setContentType("text/html;charset=utf-8");
			if (obj instanceof java.util.List) {
				response.getWriter().write(JSONArray.fromObject(obj).toString());
			} else {
				response.getWriter().write(JSONObject.fromObject(obj).toString());
			}
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setRequest(String key, Object obj) {
		request.setAttribute(key, obj);
	}

	protected String getHeander(String key) {
		return request.getHeader(key);
	}

	public static void main(String[] args) {
		String s = "asasasasa";
		System.out.println(s.indexOf("a"));
	}
}
