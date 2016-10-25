<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="commonImport.jsp"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${baseUrl}js/jquery-2.1.4.min.js" type="text/JavaScript"></script>
<script src="${baseUrl}js/jquery.json-2.4.js" type="text/JavaScript"></script>
<script src="${baseUrl}js/jquery.validate.js" type="text/JavaScript"></script>
<script src="${baseUrl}js/jquery.validate.min.js" type="text/JavaScript"></script>
<script src="${baseUrl}js/jquery-ui.js"></script>
<script src="${baseUrl}js/jquery-ui-i18n.js" type="text/JavaScript"></script>
<script src="${baseUrl}js/jquery-ui.min.js" type="text/JavaScript"></script>
<link type="text/css" href="${baseUrl}css/jquery-ui.css" rel="stylesheet" />
<link type="text/css" href="${baseUrl}css/login.css" rel="stylesheet" />
<link type="text/css" href="${baseUrl}css/common.css" rel="stylesheet" />
<link type="text/css" href="${baseUrl}css/top.css" rel="stylesheet" />
<link type="text/css" href="${baseUrl}css/userAdd.css" rel="stylesheet" />
<script data-main="${baseUrl}js/main" src="${baseUrl}js/require.js"></script>
<title>SpringMvc</title>
</head>
<body>
  <!-- head -->
  <div class="header">

    <div >
      <div style="display: inline-block"><img id="p001LogImg" src="${baseUrl}images/home/logo.png" style="width: 300px; height: 100px; margin-left: 70px;"></div>
      <div style="display: inline-block;float: right;padding-right: 30px;padding-top: 20px;"><a href="javascript:;"> <img src="${baseUrl}images/home/close.png" id="p003BackLoginBtn"/></a></div>
      <div style="display: inline-block;float: right; color: blue;padding-top: 10px;padding-left: 40px;">
            <p id="dateTimeShow"  style="padding-left: 20px; color: blue;background:url(${baseUrl}images/home/kuang.png) no-repeat;height:90px;width:250px;line-height:50px;font-size:12px;"></p>
          </div>
    </div>
  </div>

  <div class="left">
    <div class="menu">基本信息</div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="userAdd">学生信息</a>
    </div>
    <div class="menu">双色球</div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="ssq">双色球</a>
    </div>
    <div class="menu">狼人杀</div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="killing">狼人杀录入</a>
    </div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="killRateSearch">狼人杀胜率检索</a>
    </div>
  </div>


  <div class="main" id="p002WelcomeDiv">
   <div style="text-align: center; "> <h1>欢迎来到wangcyシステム管理画面</h1>
    <h4>请点击左侧的菜单选择您要进入的画面</h4></div>
  </div>

</body>
</html>