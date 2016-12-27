<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="baseUrl" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMvc</title>

</head>
<body>
  <div class="col-sm-6">
    <div class="ibox float-e-margins">
      <div class="ibox-title">
        <h5>柱状图</h5>
      </div>
      <div class="ibox-content">
        <div class="flot-chart">
          <div class="flot-chart-content" id="flot-bar-chart"></div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
