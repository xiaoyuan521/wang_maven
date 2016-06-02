<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="baseUrl" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${baseUrl}js/jquery-2.1.4.min.js"></script>
<script src="${baseUrl}js/jquery.json-2.4.js"></script>
<link type="text/css" href="${baseUrl}css/login.css" rel="stylesheet" />
<script data-main="${baseUrl}js/login" src="${baseUrl}js/require.js"></script>
<title>SpringMvc</title>
</head>
<body>

  <div id="p001loginDiv">
    <div class="tableDiv bgcolor-normal-20">
      <form id="p001loginForm">
        <table>
          <tr>
            <th></th>
            <td>
              <input type="text" id="p001UsernameTxt" name="username" />
            </td>
          </tr>
          <tr>
            <th></th>
            <td>
              <input type="password" id="p001UserPasswordTxt" name="password" />
            </td>
          </tr>
          <tr>
            <th></th>
            <td>
              <button type="button" id="p001LoginBtn" value="">login</button>
              <button type="button" id="p002WelcomeBtn" value="">welcome</button>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</body>
</html>