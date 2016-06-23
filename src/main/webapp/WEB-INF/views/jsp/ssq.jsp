<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="baseUrl" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${baseUrl}js/jquery-2.1.4.min.js"></script>
<script>
$("#sayHelloButton").on("click",function(){

    $.ajax({
        url: "/" + getContextPath() + "/sayHello",
        type: 'POST',
        dataType: "text",
        cache: false,
        success: function(data) {
		$("#sayHello").val(data);
        }
    });

});
function getContextPath() {
    var fullPath = window.location.pathname;
    var contextPath = fullPath.split("/")[1];
    return contextPath;
}


</script>
<title>双色球摇奖</title>
</head>
<body>

  <input type="button" id="click" value="点击摇双色球" />
  <div>
    <label>红球</label>
    <div id="redBall"></div>
    <label>蓝球</label>
    <div id="blueBall"></div>
  </div>


<form id="sayHelloForm">
 <input id="sayHello" type="text" value="${hello}" />
 <button id="sayHelloButton" type="button">sayHelloButton</button>
 </form>


</body>
</html>