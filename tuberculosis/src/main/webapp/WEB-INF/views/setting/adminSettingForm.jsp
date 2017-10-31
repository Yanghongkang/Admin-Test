<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>管理员信息</title>
	<script>
		$(document).ready(function() {
			$("#adminInfo-tab").addClass("active");
			
		});
	</script>
</head>

<body>
	<h1>管理员信息</h1>
	<form:form id="inputForm" modelAttribute="setting" action="${ctx}/setting/adminInfo" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${setting.id}"/>
		<fieldset>
			<legend><small>管理员信息</small></legend>
			<div id="messageBox" class="alert alert-error input-large controls" style="display:none">输入有误，请先更正。</div>
          <c:if test="${not empty message}">
            <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
          </c:if>
			<div class="control-group">
				<label for="adminName" class="control-label">姓名:</label>
				<div class="controls">
					<input type="text" id="adminName" name="adminName" value="${setting.adminName}" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="telephone" class="control-label">固定电话:</label>
				<div class="controls">
					<input type="text" id="telephone" name="telephone"  value="${setting.telephone}" class="input-large"/>
				</div>
			</div>

			<div class="control-group">
				<label for="mobile" class="control-label">手机号:</label>
				<div class="controls">
                  <input type="text" id="mobile" name="mobile"  value="${setting.mobile}" class="input-large"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="address" class="control-label">联系地址:</label>
				<div class="controls">
                  <input type="text" id="address" name="address"  value="${setting.address}" class="input-xxlarge"/>
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
