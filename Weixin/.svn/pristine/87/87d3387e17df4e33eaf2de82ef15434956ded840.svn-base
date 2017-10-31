package cn.sh.yhk.base.controller;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import commone.BaseController;

@Controller
@RequestMapping("/System")
public class SystemController extends BaseController {

	private static Logger log = Logger.getLogger(SystemController.class);

	@RequestMapping(value = "/checkSignature.do")
	public void checkSignature(String signature, String timestamp, String nonce, String echostr) {
		log.info(signature);
		log.info(timestamp);
		log.info(nonce);
		log.info(echostr);
		String[] values = { "d", timestamp, nonce };
		Arrays.sort(values); // 字典序排序
		String value = values[0] + values[1] + values[2];
		String sign = DigestUtils.shaHex(value);
		if (signature.equals(sign)) {// 验证成功返回ehcostr
			writeToResponse(echostr);
		} else {
			writeToResponse("error");
		}

	}
}
