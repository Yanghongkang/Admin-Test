<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>添加药物</title>
<script>
	$(document).ready(function() {
		$("#jiliang-tab").addClass("active");
		var json="${dosagevalList}";
	});
	
</script>
</head>
<body>
	<h1>药物剂量标准</h1>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
		<fieldset>
			<legend>
				<small>药物剂量指标</small>
			</legend>
			
			<div id="messageBox" class="alert alert-error input-large controls"
				style="display: none">输入有误，请先更正。</div>
			<form:form id="inputForm" modelAttribute="role" action="${ctx}/dosageSetting/savedosage" method="post" class="form-horizontal">
				<div class="input-prepend">
					<input type="hidden" name="id" value="${dosage.id}">
					<label>药名  </label>
	    			<input type="text" class="input-medium"  name="name" required 
	    			 value="${fn:length(dosagevalList)==4?dosage.name:''}" >
	    			<!-- onchange="if(!/^[\u4e00-\u9fa5]+$/gi.test(this.value)){alert('只能输入汉字');this.value=''}" -->
	    			
	    			<label>剂量/kg</label>
	    			<span class="add-on">0~55</span>
	    			<input type="hidden" name="mg" value="0~55">
				    <input type="number" class="input-small" name="val" min="100" step="10" required 
				    value="${fn:length(dosagevalList)==4?dosagevalList[0].value:''}" > 
				    <span class="add-on">56~70</span>
				    <input type="hidden" name="mg" value="56~70">
				    <input type="number"  class="input-small"  name="val"  min="100" step="10" required
				    value="${fn:length(dosagevalList)==4?dosagevalList[1].value:''}" >
				    <span class="add-on">70~MAX</span>
				    <input type="hidden" name="mg" value="70~MAX">
				    <input type="number"  class="input-small"  name="val"  min="100" step="10" required
				    value="${fn:length(dosagevalList)==4?dosagevalList[2].value:''}" >
				    <span class="add-on">MAX</span>
				    <input type="hidden" name="mg" value="MAX">
				    <input type="number"  class="input-small"  name="val"  min="100" step="10" required
				    value="${fn:length(dosagevalList)==4?dosagevalList[3].value:''}" >
				    <input id="submit_btn" class="btn btn-primary" type="submit" 
						value="提交"/>
				</div>
			</form:form>
			<form:form id="inputForm" modelAttribute="role" action="${ctx}/dosageSetting/savedosage" method="post" class="form-horizontal">
				<div class="input-prepend">
					<input type="hidden" name="id" value="${dosage.id}">
					<label>药名</label>
	    			<input type="text"  class="input-medium"   name="name" required
	    			value="${fn:length(dosagevalList)==5?dosage.name:''}">
	    			<!-- onchange="if(!/^[\u4e00-\u9fa5]+$/gi.test(this.value)){alert('只能输入汉字');this.value=''}" -->
	    			<label>剂量/kg</label>
	    			<span class="add-on">41~45</span>
	    			<input type="hidden" name="mg" value="41~45">
				    <input type="number"  class="input-small" name="val"  min="100"   step="10" required
				    value="${fn:length(dosagevalList)==5?dosagevalList[0].value:''}" >  
				    <span class="add-on">46~50</span>
				    <input type="hidden" name="mg" value="46~50">
				    <input type="number"  class="input-small" name="val"  min="100" step="10" required
				    value="${fn:length(dosagevalList)==5?dosagevalList[1].value:''}">
				    <span class="add-on">51~70</span>
				    <input type="hidden" name="mg" value="51~70">
				    <input type="number"  class="input-small" name="val"  min="100"  step="10" required
				    value="${fn:length(dosagevalList)==5?dosagevalList[2].value:''}">
				     <span class="add-on">70~MAX</span>
				     <input type="hidden" name="mg" value="70~MAX">
				    <input type="number"  class="input-small" name="val" min="100"  step="10" required
				    value="${fn:length(dosagevalList)==5?dosagevalList[3].value:''}">
				     <span class="add-on">MAX</span>
				     <input type="hidden" name="mg" value="MAX">
				    <input type="number"  class="input-small" name="val" min="100"  step="10" required
				    value="${fn:length(dosagevalList)==5?dosagevalList[4].value:''}">
				    <input id="submit_btn" class="btn btn-primary" type="submit"
						value="提交" />
				</div>
			</form:form>
			<form:form id="inputForm" class="form-horizontal">
			<div class="form-actions">
				 <input id="cancel_btn" class="btn"
					type="button" value="返回" onclick="history.back()" />
			</div>
			</form:form>
		</fieldset>

</body>
</html>
