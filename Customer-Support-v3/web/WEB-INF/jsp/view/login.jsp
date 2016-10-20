<%--
  Created by IntelliJ IDEA.
  User: RD
  Date: 10/14/2016
  Time: 02:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>URL Shortener</title>
</head>
<body>
<h2>Login</h2>

<% if (((Boolean)request.getAttribute("loginFailed"))){ %>
<b>The username or password you entered is incorrect.Please try again.</b><br /><br />
<%}%>

<form method="POST" action="<c:url value="/login" />">
    Username<br />
    <input type="text" name="username"/><br /><br />
    Password<br />
    <input type="password" name="password"/><br /><br />
    <input type="submit" value="Login"/>
</form>

</body>
</html>
