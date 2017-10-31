<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<i
	<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../layui/css/layui.css">
<script src="../layui/layui.all.js"></script>
<script src="../layui/jquery-1.9.1.min.js"></script>
<title>登陆 - AdminYhk</title>
</head>
<body>
<blockquote class="layui-elem-quote">Admin YHk</blockquote>
 <!--   style="background-image:url(/layui/images/log_bk.jpg);"            
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>登陆</legend>
</fieldset> -->
 
<form class="layui-form" action="/login" style="padding-left: 38%;padding-top: 11%">
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-inline">
      <input type="text" name="username" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline" style="width: 100px;">
      <input type="text" name="Vcode" lay-verify="pass" placeholder="" autocomplete="off" class="layui-input">
    </div>
     <label><img src="/VCodeServlet"></label>
  </div>
  <div class="layui-form-item">
   <label class="layui-form-label"></label>
  	<button class="layui-btn" type="submit">登陆</button>
  </div>
</form>
<script>
;!function(){
	var $ = layui.$;
	var form = layui.form
	,layer = layui.layer
	,laydate = layui.laydate;
}();
</script>
</body>
</html>