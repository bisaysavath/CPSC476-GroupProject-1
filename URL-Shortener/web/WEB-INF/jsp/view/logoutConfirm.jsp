<%--
  Created by IntelliJ IDEA.
  User: RD
  Date: 10/14/2016
  Time: 02:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>URL Shortener</title>
</head>
<body>
    <h2>Logged out succesfully!</h2><br><br>

    <a href="<c:url value="/login">
        <c:param name="action" value="login"/>
    </c:url>">Login</a>|

    <a href="<c:url value="/signup">
        <c:param name="action" value="signup"/>
    </c:url>">Sign Up</a>

</body>
</html>
