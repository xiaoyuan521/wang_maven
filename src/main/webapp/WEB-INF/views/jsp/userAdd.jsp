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
<title>SpringMvc</title>

</head>
<body>
  <div style="padding-top: 30px;">学生信息登录</div>
  <div id="p003UserAddDiv">
    <form id="p003UserAddForm" enctype="multipart/form-data">
      <table id="p003UserAddTable" style="padding-top: 10px;">
        <tr>
          <th>姓名</th>
          <td>
            <input id="p003UserNameTxt" name="username" type="text" />
          </td>
        </tr>
        <tr>
          <th>性别</th>
          <td>
            <input type="radio" name="gender" value="boy" checked="checked" id="p003GenderBoyRadio"/>
          <label>男</label>
           <input type="radio" name="gender" value="girl" id="p003GenderGirlRadio"/>
             <label>女</label>
          </td>
        </tr>
        <tr>
          <th>年龄</th>
          <td>
            <input id="p003AgeTxt" name="age" type="text" />
          </td>
        </tr>
        <tr>
          <th>成绩</th>
          <td>
            <input id="p003ScoreTxt" name="score" type="text" />
          </td>
        </tr>
        <tr>
          <th></th>
          <td>
            <button type="button" id="p003UserAddBtn">登录</button>
             <button type="button" id="p003StudentSearchBtn">查询</button>
              <button type="button" id="p003ClearBtn">Clear</button>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <form id="p003UserUploadForm" enctype="multipart/form-data" action="<%=request.getContextPath()%>/fileUpload"
    method="POST">
    <table>
      <tr>
        <th>文件上传</th>
        <td>
          <input id="p003FileUpload" name="file" type="file" />
        </td>
      </tr>
      <tr>
        <td>
          <div id="showImgDiv" style="width: 100px; height: 100px;"></div>
        </td>
      </tr>
      <tr>
        <td>
          <input type="submit" value="上传图片" />
        </td>
      </tr>
    </table>
  </form>

  <div style="padding-top: 30px;">学生信息查询</div>
    <div id="p003StudentTable" style="width:505px;"></div>
</body>
</html>
  <jsp:include page="./userEdit.jsp" />