package cn.sh.yhk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sh.yhk.commone.AdminYhkCommonConstant;
import cn.sh.yhk.util.VerifyCodeUtils;

/**
 * 
 * @author AdminYHK
 *
 */
@WebServlet(name = "VCodeServlet", urlPatterns = "/VCodeServlet")
public class VerificationCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 8031133938454140403L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		// 删除以前的
		session.removeAttribute(AdminYhkCommonConstant.SESSION_LOGIN_VCODE);
		session.setAttribute(AdminYhkCommonConstant.SESSION_LOGIN_VCODE, verifyCode.toLowerCase());
		// 生成图片
		int w = 121, h = 38;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}

}
