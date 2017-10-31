<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>用户管理</title>
	<script>
		$(document).ready(function() {
			$("#account-tab").addClass("active");
		});
	</script>
</head>

<body>
	<h1>用户管理</h1>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<div class="row">
		<div class="offset4">
			<form class="form-search" action="#">
			 	<label>登录名：</label> <input type="text" name="search_LIKE_loginName"   class="input-small"  value="${param.search_LIKE_loginName}"> 
			    <label>姓名：</label> <input type="text" name="search_LIKE_name" class="input-small" value="${param.search_LIKE_name}">
			    <button type="submit" class="btn" id="search_btn">查询</button>
                <shiro:hasPermission name="admin">
                    <a href="${ctx}/account/user/add" class="btn">新增</a>
                </shiro:hasPermission>
		    </form>
	    </div>
	</div>	
			
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>登录名</th>
			<th>姓名</th>
			<th>所属医院</th>
			<th>角色</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.loginName}</td>
				<td>${user.name}</td>
				<td>${user.recvInst}</td>
				<td>${user.roleNames}</td>
				<td>${allStatus[user.status]}</td>
				<td>
                    <shiro:hasPermission name="admin">
						<a href="${ctx}/account/user/update/${user.id}" id="editLink-${user.loginName}">修改</a>&nbsp;&nbsp;
                        <a href="${ctx}/profile/${user.id}" id="editLink-${user.loginName}">修改密码</a>&nbsp;&nbsp;
                        <a href="${ctx}/account/user/delete/${user.id}" id="editLink-${user.loginName}" onclick="return del()">删除</a>
                    </shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>		
	</table>
    <script type="text/javascript">
        function del(){
            return window.confirm("确定要删除吗？");
        }
    </script>
</body>
</html>
