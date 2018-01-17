var mean = "	<div class=\"layui-logo\">AmdinYhk</div>"
		+ "			<ul class=\"layui-nav layui-layout-left\" id=\"mean\">"
		+ "				<li class=\"layui-nav-item\"><a href=\"javascript:;\">控制台</a></li>"
		+ "				<li class=\"layui-nav-item\"><a href=\"\">商品管理</a></li>"
		+ "				<li class=\"layui-nav-item\"><a href=\"\">用户</a></li>"
		+ "				<li class=\"layui-nav-item\"><a href=\"javascript:;\">系统管理</a>"
		+ "					<dl class=\"layui-nav-child\">" + "						<dd>"
		+ "							<a href=\"javascript:;\">资源管理</a>" + "						</dd>"
		+ "						<dd>" + "							<a href=\"\">消息管理</a>" + "						</dd>"
		+ "						<dd>" + "							<a href=\"\">授权管理</a>" + "						</dd>"
		+ "					</dl></li>" + "			</ul>"
		+ "			<ul class=\"layui-nav layui-layout-right\">"
		+ "				<li class=\"layui-nav-item\"><a href=\"javascript:;\"> <img"
		+ "						src=\"/layui/images/1111.jpg\" class=\"layui-nav-img\">Yhk"
		+ "				</a>" + "					<dl class=\"layui-nav-child\">" + "						<dd>"
		+ "							<a href=\"\">基本资料</a>" + "						</dd>" + "						<dd>"
		+ "							<a href=\"\">安全设置</a>" + "						</dd>" + "					</dl></li>"
		+ "				<li class=\"layui-nav-item\"><a href=\"\">退了</a></li>"
		+ "			</ul>";
// <li class='layui-nav-item'><a href='javascript:;'>控制台</a></li>
// <li class='layui-nav-item'><a href=''>商品管理</a></li>
// <li class='layui-nav-item'><a href=''>用户</a></li>
// <li class='layui-nav-item'><a href='javascript:;'>系统管理</a>
// <dl class='layui-nav-child'>
// <dd>
// <a href='javascript:;'>资源管理</a>
// </dd>
// <dd>
// <a href=''>消息管理</a>
// </dd>
// <dd>
// <a href=''>授权管理</a>
// </dd>
// </dl></li>
layui.use([ 'jquery' ], function() {
	layui.$("#mean").append(mean);
});