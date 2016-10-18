<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="baseUrl" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${baseUrl}js/jquery.validate.js" type="text/JavaScript"></script>
<script src="${baseUrl}js/jquery.validate.min.js" type="text/JavaScript"></script>
<link type="text/css" href="${baseUrl}css/common.css" rel="stylesheet" />
<title>狼人杀胜率查询</title>
</head>
<body>
  <div style="padding-top: 30px;">狼人杀胜率查询</div>
  <div>
    <form id="p005KillRateSearchForm">
      <table style="padding-top: 10px;">
        <tr>
          <th>玩家姓名</th>
          <td>
            <select style="width: 200px" id="p005PlayerNameSelect" name="name">
            </select>
          </td>
        </tr>
        <tr>
          <th>玩家角色</th>
          <td>
            <select style="width: 200px" id="p005RoleSelect" name="roleId">
            </select>
          </td>
        </tr>
        <tr>
          <th>日期</th>
          <td>
            <input id="p005Date" name="date" type="text" style="width: 200px" />
          </td>
        </tr>
        <tr>
          <th>胜负状况</th>
          <td>
            <input type="radio" name="gameStatus" value="" checked="checked" id="p005A;;Radio" />
            <label>所有</label>
            <input type="radio" name="gameStatus" value="equal" id="p005EqualRadio" />
            <label>平</label>
            <input type="radio" name="gameStatus" value="success" id="p005SuccessRadio" />
            <label>胜利</label>
            <input type="radio" name="gameStatus" value="fail" id="p005FailRadio" />
            <label>失败</label>
          </td>
        </tr>
        <tr>
          <td>
            <button type="button" id="p005SearchBtn">查询</button>
          </td>
        </tr>
      </table>
    </form>
  </div>
  <div style="padding-top: 30px;" id="p005PlayerRateSearch"></div>
  <div id="p005InformationTable" style="width: 300px;"></div>
</body>
</html>