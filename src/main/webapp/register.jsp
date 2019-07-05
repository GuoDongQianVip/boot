<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/7/4
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function toLogin(){
            location.href="login.jsp";
        }
    </script>
</head>
<body>
    <h1 align="center">注册页面</h1>
    <center><form action="/user/register" method="post">
        用户名:<input type="text" name="userName"><br>
        密码:<input type="password" name="userPswd"><br>
        <input type="submit" value="注册">
        <input type="button" value="登录" onclick="toLogin()">
    </form></center>
</body>
</html>
