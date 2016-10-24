<%--
  Created by IntelliJ IDEA.
  User: RD
  Date: 10/14/2016
  Time: 06:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>URL Shortener</title>
</head>
<body>
    <% if (request.getSession().getAttribute("username") == null){%>

        <a href="<c:url value="/login">
            <c:param name = "action" value="login"/>
            </c:url>">Login</a>|

        <a href="<c:url value="/signup">
            <c:param name="action" value="signup"/>
            </c:url>">Sign Up</a>
    <%}%>

    <h2>Sign Up</h2>

    <% if ((Boolean)request.getAttribute("signUpFailed")){%>
        <b>Sign Up failed. Please try again.</b><br /><br />
    <%}%>

    <form method="POST" action="<c:url value="/login" />">
        <input type="hidden" name="action" value="newUser"/>
        Username<br />
        <input type="text" name="username" required/><br /><br />
        Password<br />
        <input type="password" name="password" required/><br /><br />
        Confirm Password<br />
        <input type="password" name="conpasswd" required/><br /><br />
        <input type="submit" value="Sign Up" />
    </form>

</body>
</html>
