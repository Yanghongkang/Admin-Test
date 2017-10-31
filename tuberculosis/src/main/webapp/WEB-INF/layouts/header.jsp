<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="header" class="row">
	<div><h1>结核病患者用户管理系统</h1></div>
	<div class="pull-right">
	        <shiro:user>
				你好，<shiro:principal property="name"/> &nbsp;&nbsp;<a href="${ctx}/logout">注销</a>
			</shiro:user>
	</div>
</div>
