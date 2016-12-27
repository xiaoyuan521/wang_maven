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
</head>
<body>
  <div>
    <form id="p005KillRateSearchForm">
      <table style="padding-top: 30px;">

        <tr id="p005PlayerNameChartGraphTr">
          <th>玩家姓名</th>
          <td>
            <select style="width: 200px" id="p005PlayerNameChartGraphSelect" name="name">
            </select>
          </td>
        </tr>

        <tr>
          <td>
            <button type="button" id="p005chartGraphSearchBtn">个人记录</button>
          </td>
        </tr>
      </table>
    </form>
  </div>
  <div style="padding-top: 30px;" id="p005PlayerRateSearch"></div>
  <div id="p005InformationTable" style="width: 1300px;"></div>
  <div id="p005PlayerTable" style="width: 381px; display: none;"></div>
  <div class="row">
    <div class="col-sm-6" id="p005chartGraphDiv">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>饼状图</h5>
        </div>
        <div class="ibox-content">
          <div class="flot-chart" style="height: 200px; width: 1654px">
            <div class="flot-chart-content" id="flot-pie-chart"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>