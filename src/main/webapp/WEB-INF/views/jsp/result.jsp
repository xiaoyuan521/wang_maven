<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="baseUrl" />
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
<script type="text/javascript" src="${baseUrl}js/plupload.js"></script>
<script type="text/javascript" src="${baseUrl}js/plupload.gears.js"></script>
<script type="text/javascript" src="${baseUrl}js/plupload.silverlight.js"></script>
<script type="text/javascript" src="${baseUrl}js/plupload.flash.js"></script>
<script type="text/javascript" src="${baseUrl}js/plupload.browserplus.js"></script>
<script type="text/javascript" src="${baseUrl}js/plupload.html4.js"></script>
<script type="text/javascript" src="${baseUrl}js/plupload.html5.js"></script>
<link type="text/css" href="${baseUrl}css/jquery-ui.css" rel="stylesheet" />
<link type="text/css" href="${baseUrl}css/common.css" rel="stylesheet" />
<link type="text/css" href="${baseUrl}css/top.css" rel="stylesheet" />
<link type="text/css" href="${baseUrl}css/userAdd.css" rel="stylesheet" />
<link type="text/css" href="${baseUrl}css/killerAdd.css" rel="stylesheet" />
<script data-main="${baseUrl}js/main" src="${baseUrl}js/require.js"></script>
<title>上传结果</title>
</head>
<body>
  <div style="display: inline-block; float: right; color: blue; padding-top: 10px; padding-left: 40px;" id="dataShowDiv">
    <p id="dateTimeShow"
      style="padding-left: 20px; color: blue;background:url(${baseUrl}images/home/kuang.png) no-repeat;height:90px;width:250px;line-height:50px;font-size:12px;"></p>
  </div>
  <div>
    <div>
      <label>上传文件之后，刷新一下工程目录，然后再刷新一下页面，就可以显示出来最新的上传的图片了</label>
    </div>
    <img alt="" src="${baseUrl}upload/${fileName}" id="p004ShowImg" />
    <input type="hidden" id="p004FileNmme" value="${fileName}" />
  </div>

  <div>
    <input type="button" id="p004RefeshBtn" value="刷新" />
    <button type="button" id="p004BackLoginBtn" value="">backToLogin</button>
    <button type="button" id="p004DownloadImgBtn" value="">下载</button>
  </div>
</body>
</html>