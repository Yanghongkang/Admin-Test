<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>用户管理-增加</title>
	<script>
		$(document).ready(function() {
			$("#account-tab").addClass("active");
			
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						remote: "${ctx}/account/user/checkNewName"
					}
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
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
	<h1>用户管理--增加</h1>
	<form:form id="inputForm"  action="${ctx}/account/user/add" method="post" class="form-horizontal" >
		<fieldset>
			<legend><small>用户管理</small></legend>
			<div id="messageBox" class="alert alert-error input-large controls" style="display:none">输入有误，请先更正。</div>
			<div class="control-group">
				<label for="loginName" class="control-label">登录名:</label>
				<div class="controls">
					<input type="text" id="loginName" name="loginName" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" id="name" name="name" class="input-large required"/>
				</div>
			</div>
          <div class="control-group">
            <label for="recvInst" class="control-label">所属医院:</label>
            <div class="controls">
              <input type="text" id="recvInst" name="recvInst" class="input-large required"/>
            </div>
          </div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="plainPassword" name="plainPassword" class="input-large" placeholder="...不修改密码，请勿填写"/>
				</div>
			</div>
            <div class="control-group">
                <label for="confirmPassword" class="control-label">确认密码:</label>
                <div class="controls">
                    <input type="password" id="confirmPassword" name="confirmPassword" class="input-large required" equalTo="#plainPassword"/>
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
