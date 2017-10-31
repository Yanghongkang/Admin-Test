<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<div class="row">
		<div class="offset4">
			<form class="form-search" action="#">
			 	<label>医院名称：</label> <input type="text" name="search_LIKE_name"   class="input-small"  value="${param.search_LIKE_name}">
			    <button type="submit" class="btn" id="search_btn">查询</button>
                    <a href="${ctx}/setting/hospital/add" class="btn">新增</a>
		    </form>
	    </div>
	</div>	
			
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>医院名称</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${hospitals}" var="hospital">
			<tr>
				<td>${hospital.name}</td>
				<td>
                    <a href="${ctx}/setting/hospital/update/${hospital.id}" id="editLink-${hospital.name}">修改</a>&nbsp;&nbsp;
                    <a href="${ctx}/setting/hospital/delete/${hospital.id}" id="editLink-${hospital.name}" onclick="return del()">删除</a>
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
