<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<link rel="stylesheet" href="../layui/css/layui.css">
<script src="../layui/layui.all.js"></script>
<script src="../layui/jquery-1.9.1.min.js"></script>

<title>首页</title>
</head>
<body>
<fieldset class="layui-elem-field site-demo-button" style="">
  <legend>条件过滤</legend>
  <div style="padding: 5px">
    <label class="">姓名</label>
    <div class="layui-input-inline">
      <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
     <button class="layui-btn"><i class="layui-icon">&#xe615;</i> 搜索</button>
  </div>
</fieldset>
<fieldset class="layui-elem-field site-demo-button" style="">
  <legend>管理操作</legend>
  <div style="padding: 5px">
  <button class="layui-btn"><i class="layui-icon">&#xe654;</i> 添加</button>
  </div>
</fieldset>

<table style="" class="layui-table" lay-data="{url:'/User/searchuserlist',height:'full-250',page:true}" lay-filter="demo">
  <thead>
    <tr>
      <th lay-data="{field:'id', width:80, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'userName', width:100}">用户名</th>
      <th lay-data="{field:'lastLoginDate', width:100}">最后登陆时间</th>
    </tr>
  </thead>
</table>
</body>
</html>