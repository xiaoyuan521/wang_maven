<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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