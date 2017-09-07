<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="commonImport.jsp"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<script src="<%=basePath%>/js/jquery-2.1.4.min.js" type="text/JavaScript"></script>
<script src="<%=basePath%>/js/jquery.json-2.4.js" type="text/JavaScript"></script>
<script src="<%=basePath%>/js/jquery.validate.js" type="text/JavaScript"></script>
<script src="<%=basePath%>/js/jquery.validate.min.js" type="text/JavaScript"></script>
<script type="text/javascript" src="<%=basePath%>/js/plupload.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/plupload.gears.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/plupload.silverlight.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/plupload.flash.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/plupload.browserplus.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/plupload.html4.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/plupload.html5.js"></script>
<link rel="icon" href="<%=basePath%>/images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=basePath%>/images/favicon.ico" type="image/x-icon" />
<link type="text/css" href="<%=basePath%>/css/jquery-ui.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>/css/login.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>/css/common.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>/css/top.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>/css/userAdd.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>/css/killerAdd.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>/css/forum.css" rel="stylesheet" />
<link href="<%=basePath%>/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link href="<%=basePath%>/css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_blank">
<script src="<%=basePath%>/js/bootstrap.min.js?v=3.3.5"></script>
<script src="<%=basePath%>/js/plugins/jquery.flot.js"></script>
<script src="<%=basePath%>/js/plugins/jquery.flot.tooltip.min.js"></script>
<script src="<%=basePath%>/js/plugins/jquery.flot.resize.js"></script>
<script src="<%=basePath%>/js/plugins/jquery.flot.pie.js"></script>
<script src="<%=basePath%>/js/content.min.js?v=1.0.0"></script>
<script src="<%=basePath%>/js/jquery-ui.js"></script>
<script src="<%=basePath%>/js/jquery-ui-i18n.js" type="text/JavaScript"></script>
<script src="<%=basePath%>/js/jquery-ui.min.js" type="text/JavaScript"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script data-main="<%=basePath%>/js/main" src="<%=basePath%>/js/require.js"></script>
<title>SpringMvc</title>
</head>
<body>
  <!-- head -->
  <div class="header">

    <div class="navbar-inner">
      <div style="display: inline-block">
        <img id="p001LogImg" src="<%=basePath%>/images/home/logo.png"
          style="width: 300px; height: 100px; margin-left: 70px;">
      </div>
      <div style="display: inline-block; float: right; padding-right: 30px; padding-top: 20px;">
        <a href="javascript:;" target="_self"> <img src="<%=basePath%>/images/home/close.png" id="p003BackLoginBtn" /></a>
      </div>
      <div style="display: inline-block; float: right; color: blue; padding-top: 10px; padding-left: 40px;">
        <p id="dateTimeShow"
          style="padding-left: 20px; color: blue;background:url(<%=basePath%>/images/home/kuang.png) no-repeat;height:90px;width:250px;line-height:50px;font-size:12px;"></p>
      </div>
    </div>
  </div>

  <div class="left">

    <!-- 菜单项目区域 --><div id="menu_container" style="overflow-y:auto;"></div>

    <div class="menu" id="userInformationMenu">基本信息</div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="userAdd" target="_self" style="display:none" id="userAddSubMenu">学生信息</a>
    </div>
    <div class="menu" id="ssqMenu">双色球</div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="ssq" target="_self"  style="display:none" id="ssqSubMenu">双色球</a>
    </div>
    <div class="menu" id="werewolfMenu">狼人杀</div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="killerAdd" target="_self"  style="display:none" id="killerSubMenu">玩家信息录入</a>
    </div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="killing" target="_self"  style="display:none" id="killAddSubMenu">狼人杀录入</a>
    </div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="killRateSearch" target="_self"  style="display:none"id="killRateSearchSubMenu">狼人杀记录查询</a>
    </div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="barGraph" target="_self"  style="display:none" id="barGraphSubMenu">狼人杀柱状图</a>
    </div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="pieGraph" target="_self"  style="display:none" id="pieGraphSubMenu">个人饼图查询</a>
    </div>

    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="lineGraph" target="_self"  style="display:none" id="lineGraphSubMenu">狼人杀折线图</a>
    </div>
    <div class="menu"id="forumMenu">论坛中心</div>
    <div class="subMenu">
      <a href="javascript:void(0)" data-pagename="forum" target="_self"  style="display:none" id="forumSubMenu">论坛</a>
    </div>
  </div>

  <div class="main" id="p002WelcomeDiv">
    <div style="text-align: center;">
      <h1>欢迎来到wangcyシステム管理画面</h1>
      <h4>请点击左侧的菜单选择您要进入的画面</h4>
    </div>
  </div>

</body>
</html>