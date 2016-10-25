<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="baseUrl" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Microad狼人杀系统</title>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<link type="text/css" href="<%=basePath%>/css/login.css" rel="stylesheet" />
<link rel="icon" href="<%=basePath%>/images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=basePath%>/images/favicon.ico" type="image/x-icon" />
<script src="<%=basePath%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=basePath%>/js/jquery.json-2.4.js"></script>
<script data-main="<%=basePath%>/js/login" src="<%=basePath%>/js/require.js"></script>
</head>
<body bgcolor="#d7dfe1" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
  <div class="loginMain">
    <div class="loginHeader">
      <div class="back_top">
        <span id="dateShow"></span>
      </div>
    </div>
    <div class="background3"></div>
    <div class="background4"></div>
    <div class="background5"></div>
    <div class="inputContent">
      <div class="nameandpassword">
        <span>用户名：</span>
        <input type="text" id="p001UsernameTxt" name="username" class="username" />

        <span id="spname" class="wenzimiaoshu">请输入用户名</span>
      </div>
      <div class="nameandpassword">
        <span>密码：</span>
        <input type="password" id="p001UserPasswordTxt" name="password" type="password" class="password" />
        <span id="sppassword" class="wenzimiaoshu" style="top: 3px;">请输入登录密码</span>
      </div>
    </div>
    <div class="button">
      <div id="p001LoginBtn" class="loginbottom">
        <span>登 录</span>
        <a href="javascript:void(0)" id="p002WelcomeBtn">welcome</a>
      </div>

    </div>
    <div class="background8"></div>
    <div class="loginFooter">
      <img src="<%=basePath%>/images/login/logo.png">
    </div>
  </div>
</body>
</html>