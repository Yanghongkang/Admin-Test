<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="leftbar" class="span2">
    <shiro:hasPermission name="mng:title">
	<h1>用户管理</h1>
    </shiro:hasPermission>

	<div class="submenu">
        <shiro:hasPermission name="mng:user">
		    <a id="account-tab"href="${ctx}/account/user/">用户管理</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="mng:role">
            <a id="role-tab" href="${ctx}/account/role/">角色管理</a>
        </shiro:hasPermission>
	</div>
  <br/>
  <shiro:hasPermission name="setting:adminInfo">
    <h1>设置</h1>
  </shiro:hasPermission>
  <div class="submenu">
    <shiro:hasPermission name="setting:adminInfo">
      <a id="adminInfo-tab"href="${ctx}/setting/adminInfo/">管理员信息</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="setting:hospital">
      <a id="hospital-tab"href="${ctx}/setting/hospital/">医院信息</a>
    </shiro:hasPermission>
  </div>
  <br/>
  <shiro:hasPermission name="setting:diagnosis">
    <h1 style="width:150px">治疗检测项目及频率</h1>
  </shiro:hasPermission>
  <div class="submenu">
    <shiro:hasPermission name="setting:diagnosis">
      <%--<a id="fei-tab"href="${ctx}/diagnosisSetting/fei/">非结核</a>--%>
      <%--<a id="putongchu-tab"href="${ctx}/diagnosisSetting/putongchu/">普通结核初治</a>--%>
      <%--<a id="putongfu-tab"href="${ctx}/diagnosisSetting/putongfu/">普通结核复治</a>--%>
      <%--<a id="danny-tab"href="${ctx}/diagnosisSetting/danny/">单耐药结核</a>--%>
      <%--<a id="duony-tab"href="${ctx}/diagnosisSetting/duony/">多耐药结核</a>--%>
      <a id="naiduoyao-tab"href="${ctx}/diagnosisSetting/naiduoyao/">耐多药结核</a>
      <a id="guangfanny-tab"href="${ctx}/diagnosisSetting/guangfanny/">广泛耐药结核</a>
      <a id="jiliang-tab"href="${ctx}/dosageSetting/jiliang/">结核药物剂量提醒</a>
    </shiro:hasPermission>
  </div>
  <br/>

</div>