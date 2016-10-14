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
<title>杀人游戏数据录入</title>
</head>
<body>
  <div style="padding-top: 30px;">狼人杀基本信息录入</div>
  <div>
    <form id="p004KillingAddForm">
      <table style="padding-top: 10px;">
        <tr>
          <th>玩家姓名</th>
          <td>
           <select style="width:200px" id="p004PlayerNameSelect" name="name">
           </select>
          </td>
        </tr>
        <tr>
          <th>玩家角色</th>
          <td>
            <select style="width:200px" id="p004RoleSelect" name="roleId">
           </select>
          </td>
        </tr>
        <tr>
          <th>日期</th>
          <td>
            <input id="p004Date" name="date" type="text" style="width:200px"  />
          </td>
        </tr>
        <tr>
          <th>胜负状况</th>
          <td>
            <input type="radio" name="gameStatus" value="equal" checked="checked" id="p004EqualRadio"/><label>平</label>
            <input type="radio" name="gameStatus" value="success" id="p004SuccessRadio"/><label>胜利</label>
            <input type="radio" name="gameStatus" value="fail"id="p004FailRadio"/><label>失败</label>
          </td>
        </tr>
        <tr>
        <td>
            <button type="button" id="p004AddBtn" >录入</button>
            <button type="button" id="p004SearchBtn" >胜率查询</button>
          </td>
        </tr>
      </table>
    </form>
  </div>
   <div style="padding-top: 30px;" id="p004PlayerInforInsertSearch"></div>
  <div id="p004PlayerTable" style="width: 381px;"></div>
   <div id="p004InformationTable" style="width: 300px;"></div>
</body>
</html>