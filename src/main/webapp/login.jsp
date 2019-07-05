<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/7/4
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function toRegister(){
            location.href = "register.jsp";
        }
    </script>
</head>
<body>
    <h1 align="center">登录页面</h1>
    <center><form action="/user/login" method="post">
        用户名:<input type="text" name="userName"><br>
        密码:<input type="password" name="userPswd"><br>
        <input type="submit" value="登录">
        <input type="button" value="注册" onclick="toRegister()">
    </form></center>
</body>
</html>
