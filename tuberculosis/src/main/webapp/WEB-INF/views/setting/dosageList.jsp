<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>剂量提醒</title>
	<script>
		$(document).ready(function() {
			$("#jiliang-tab").addClass("active");
		});
	</script>
</head>

<body>
	<h1>剂量提醒</h1>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	 <div class="row">
		<div class="offset4">
			<form class="form-search" action="#">
			 	<label>药物名：</label> <input type="text" name="search_LIKE_name"   class="input-small"  value="${param.search_LIKE_name}">
			    <button type="submit" class="btn" id="search_btn">查询</button>
                <shiro:hasPermission name="admin">
                  <a href="${ctx}/dosageSetting/add" class="btn">新增</a>
                </shiro:hasPermission>
		    </form>
	    </div>
	</div>	
			
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
            <%--<th>ID</th>--%>
			<th>药物名</th>
			<th>添加时间</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${dosages}" var="dosages">
			<tr>
				<%--<td>${role.id}</td>--%>
				<td>${dosages.name}</td>
				<td>${dosages.value}</td>
				<td>
                    <shiro:hasPermission name="admin">
						<a href="${ctx}/dosageSetting/update/${dosages.id}" id="editLink-${dosages.name}">修改</a> &nbsp;&nbsp;
                        <a href="${ctx}/dosageSetting/delete/${dosages.id}" id="editLink-${dosages.name}" onclick="return del()">删除</a>
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
