<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>医院管理</title>
	<script>
		$(document).ready(function() {
			$("#hospital-tab").addClass("active");
			
		});
	</script>
</head>
<body>
	<h1>医院管理</h1>
	<form:form id="inputForm" modelAttribute="hospital" action="${ctx}/setting/hospital/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${hospital.id}"/>
		<fieldset>
			<legend><small>医院管理</small></legend>
			<div id="messageBox" class="alert alert-error input-large controls" style="display:none">输入有误，请先更正。</div>
			<div class="control-group">
				<label for="name" class="control-label">医院名:</label>
				<div class="controls">
					<input type="text" id="name" name="name"  value="${hospital.name}" class="input-large required"/>
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
