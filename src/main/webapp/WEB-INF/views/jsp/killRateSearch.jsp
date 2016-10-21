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
  <div style="padding-top: 30px;" id="p005RateSearch">狼人杀胜率查询</div>
  <div style="padding-top: 30px;"id="p005RecordSearch">狼人杀游戏记录查询</div>
  <div style="padding-top: 30px;">
    <table>
      <tr>
        <th>请选择服务项目</th>
        <td>
          <div style="display: inline-block;">
            <input type="radio" name="functionCheck" value="rate" checked="checked" id="p005functionRateRadio" />
            <label for="p005functionRateRadio">狼人杀胜率查询</label>
          </div>
          <div style="display: inline-block;">
            <input type="radio" name="functionCheck" value="record" id="p005RecordRadio" />
            <label for="p005RecordRadio">狼人杀游戏记录查询</label>
          </div>
        </td>
      </tr>
    </table>
  </div>
  <div>
    <form id="p005KillRateSearchForm">
      <table style="padding-top: 30px;">
        <tr id="p005PlayerNameTr">
          <th>玩家姓名</th>
          <td>
            <select style="width: 200px" id="p005PlayerNameSelect" name="name">
            </select>
          </td>
        </tr>
        <tr id="p005RoleTr">
          <th>玩家角色</th>
          <td>
            <select style="width: 200px" id="p005RoleSelect" name="roleId">
            </select>
          </td>
        </tr>
        <tr id="p005RoleDateTr">
          <th>日期</th>
          <td>
            <input id="p005Date" name="date" type="text" style="width: 200px" />
          </td>
        </tr>
        <tr id="p005GameStatusTr">
          <th>胜负状况</th>
          <td>
            <input type="radio" name="gameStatus" value="" checked="checked" id="p005AllRadio" />
            <label for="p005AllRadio">所有</label>
            <input type="radio" name="gameStatus" value="equal" id="p005EqualRadio" />
            <label for="p005EqualRadio">平</label>
            <input type="radio" name="gameStatus" value="success" id="p005SuccessRadio" />
            <label for="p005SuccessRadio">胜利</label>
            <input type="radio" name="gameStatus" value="fail" id="p005FailRadio" />
            <label for="p005FailRadio">失败</label>
          </td>
        </tr>
        <tr>
          <td>
            <button type="button" id="p005RateSearchBtn">胜率查询</button>
             <button type="button" id="p005RecordSearchBtn">游戏记录查询</button>
          </td>
        </tr>
      </table>
    </form>
  </div>
  <div style="padding-top: 30px;" id="p005PlayerRateSearch"></div>
  <div id="p005InformationTable" style="width: 1300px;"></div>
    <div id="p005PlayerTable" style="width: 381px;display:none;"></div>

</body>
</html>