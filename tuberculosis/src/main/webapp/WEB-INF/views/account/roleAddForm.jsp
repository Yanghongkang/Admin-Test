<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>角色管理-增加</title>
	<script>
		$(document).ready(function() {
			$("#role-tab").addClass("active");
			
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					name: {
						remote: "${ctx}/account/role/checkNewRoleName"
					}
				},
				messages: {
                    name: {
						remote: "角色名已存在"
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					if ( element.is(":checkbox") )
						error.appendTo(element.parent().next());
					else
						error.insertAfter(element);
				}
			});
		});
	</script>
</head>

<body>
	<h1>角色管理-增加</h1>
	<form:form id="inputForm" modelAttribute="role" action="${ctx}/account/role/add" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>角色管理</small></legend>
			<div id="messageBox" class="alert alert-error input-large controls" style="display:none">输入有误，请先更正。</div>
			<div class="control-group">
				<label for="name" class="control-label">角色名:</label>
				<div class="controls">
					<input type="text" id="name" name="name"  class="input-large required"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form:form>
</body>
</html>
