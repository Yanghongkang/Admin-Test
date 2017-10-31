package cn.sh.yhk.commone;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
/**
 * 
* @ClassName: BaseController Contrller基类
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author AdminYHK(Yanghongkang)
* @date 2017年5月18日 下午8:53:03 
*
 */
public class AdminYhkBaseController {

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
			response.getWriter().write(JSON.toJSONString(obj));
//			if (obj instanceof java.util.List) {
//				response.getWriter().write(JSONArray.fromObject(obj).toString());
//				
//			} else {
//				response.getWriter().write(JSONObject.fromObject(obj).toString());
//			}
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
}
