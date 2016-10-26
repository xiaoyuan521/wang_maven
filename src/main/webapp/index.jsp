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

  <script type="text/javascript">
            function getContextPath() {
                var fullPath = window.location.pathname;
                var contextPath = fullPath.split("/")[1];
                return contextPath;
            }
            window.location.href = "/" + getContextPath() + "/login";
        </script>

</body>
</html>