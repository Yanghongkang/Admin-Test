package commone;

//服务层异常枚举
public enum ServerStatusEnum {
	SUCC("0000","成功"),
	
	/**系统错误描述定义**/
	SYSERROR("9999","系统错误!"),
	SYS1000("SYS1000","服务层接受参数不能为空!"),
	SYS1001("SYS1001","ACCESS_TOKEN验证失败!"),
	SYS1002("SYS1002","调用方法不明确!"),
	SYS1003("SYS1003","方法调用失败!"),
	
	SYSERROR9901("9901","参数校验异常!"),
	SYSERROR9903("9903","方法调用不明确!"),
	SYSERROR9904("9904","参数处理异常!"),
	
	/* 
	 * commone
	 */
	
	SYS0500("SYS0500", "时间不能为空"),
	SYS0501("SYS0501", "内容不能为空"),
	SYS2("SYS2", "保存成功"),
	
	
	/*
	 *  诉求单常规返回标记
	 */
	EXPEND0001("EXPEND0001", "金额不能为空"),
	RPINFO0501("RPINFO0501", "诉求单保存失败"),
	RPINFO0502("RPINFO0502", "诉求单附件记录保存成功"),
	RPINFO0503("RPINFO0503", "诉求单附件记录保存失败"),
	//RPINFO0504("RPINFO0504", "诉求单附件记录保存成功"),
	RPINFO0505("RPINFO0505", "诉求单查询成功"),
	RPINFO0506("RPINFO0506", "诉求单历史查询成功"),
	RPINFO0507("RPINFO0507", "诉求单详情查询成功"),
	RPINFO0508("RPINFO0508", "诉求单办结查询成功"),
	RPINFO0509("RPINFO0509", "诉求单办理查询成功"),
	RPINFO0510("RPINFO0510", "附件上传成功"),
	RPINFO0511("RPINFO0511", "附件上传失败"),
	RPINFO0512("RPINFO0512", "诉求单查询其下某二级单位明细查询成功"),
	RPINFO0513("RPINFO0513", "诉求单查询其下某二级单位明细失败"),
	RPINFO0514("RPINFO0514", "诉求单查询失败"),
	RPINFO0515("RPINFO0515", "诉求单详情查询失败"),
	RPINFO0516("RPINFO0516", "诉求单历史查询失败"),
	RPINFO0517("RPINFO0517", "抱歉！当天只能填写五张诉求"),
	RPINFO0518("RPINFO0518", "检查成功"),
	
	/**回执信息返回记录**/
	RECEO0500("RECEO0500", "回执信息详情查询成功"),
	RECEO0501("RECEO0501", "回执信息详情查询成功"),
	RECEO0502("RECEO0502", "获取未读回执信息数成功"),
	RECEO0503("RECEO0503", "更新回执信息状态成功"),
	RECEO0504("RECEO0504", "回执信息详情查询失败"),
	RECEO0505("RECEO0505", "回执信息详情查询失败"),
	RECEO0506("RECEO0506", "获取未读回执信息数失败"),
	RECEO0507("RECEO0507", "更新回执信息状态失败"),
	
	/**工单满意度返回记录**/
	SATIO0500("SATIO0500", "添加工单满意度成功"),
	SATIO0501("SATIO0501", "满意度查询成功"),
	SATIO0502("SATIO0502", "添加工单满意度失败,请确认当前工单是否办结"),
	SATIO0503("SATIO0503", "满意度查询失败"),
	SATIO0504("SATIO0504", "评价时间已超过15天，不能再评价"),
	SATIO0505("SATIO0505", "工单尚未办结,无法保存满意度"),
	
	/**区县信息返回记录**/
	CODE00500("CODE00500", "查询区县信息成功"),
	CODE00501("CODE00501", "查询区县信息失败"),
	
	
	/**人员信息描述定义**/
	USER2000("USER2000", "注册成功,前往注册邮箱激活"),
	USER2001("USER2001", "注册失败"),
	USER2002("USER2002", "手机激活码已超时"),
	USER2003("USER2003", "手机激活码校验不通过"),
	USER2004("USER2004", "该手机号已被注册"),
	USER2005("USER2005", "该用户名已被注册"),
	USER2006("USER2006", "用户名不足五位"),
	USER2007("USER2007", "用户名不满为空"),
	USER2008("USER2008", "无效邮箱名"),
	
	USER2100("USER2100", "短信发送成功"),
	USER2101("USER2101", "短信发送失败"),
	USER2102("USER2102", "短信保存失败"),
	USER2103("USER2103", "短信1分钟内不可重复发送"),
	
	USER2200("USER2200", "修改成功"),
	USER2201("USER2201", "修改失败"),
	USER2202("USER2202", "该用户名不存在"),
	
	
	
	USER2300("USER2300", "手机号修改成功"),
	USER2301("USER2301", "手机号修改失败"),
	USER2302("USER2302", "手机激活码已超时"),
	USER2303("USER2303", "手机激活码校验不通过"),
	USER2304("USER2304", "该手机号已被注册"),
	USER2305("USER2305", "该用户名不存在"),
	USER2306("USER2306", "该用户名未激活，请前往邮箱激活！"),
	
	USER2400("USER2400", "登陆成功"),
	USER2401("USER2401", "登陆失败"),
	USER2402("USER2402", "账号密码错误"),
	USER2403("USER2403", "账号已被锁定"),
	USER2404("USER2404", "密码输入错误5次，请通过忘记密码功能找回密码"),
	
	USER2500("USER2500", "退出成功"),
	USER2501("USER2501", "退出失败"),
	USER2502("USER2502", "用户不存在"),
	
	USER2600("USER2600", "密码修改成功"),
	USER2601("USER2601", "密码修改失败"),
	USER2602("USER2602", "用户密码校验不通过"),

	USER2700("USER2700", "密码重置成功"),
	USER2701("USER2701", "密码重置失败"),
	USER2702("USER2702", "短信发送失败"),
	USER2703("USER2703", "短信保存失败"),
	USER2704("USER2704", "预留手机号不匹配"),
	
	USER2705("USER2705", "用户须知已读更新成功"),
	USER2706("USER2706", "用户须知已读更新失败"),
	
	USER2800("USER2800", "用户名校验通过"),
	USER2900("USER2900", "手机号校验通过");
	
	private String index;
	private String name;
	ServerStatusEnum(String index,String name){
		this.index=index;
		this.name=name;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
