<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>URL Shortener</title>
</head>
<body>
<div class="row">
	<div class="col-md-6 col-md-offset-3">
	    <% if (request.getSession().getAttribute("username") == null){%>
	
	        <a href="<c:url value="/login">
	            <c:param name = "action" value="login"/>
	            </c:url>">Login</a> |
	
	      <%--  <a href="<c:url value="/signup">
	            <c:param name="action" value="signup"/>
	            </c:url>">Sign Up</a>|
	           --%>  
	        <a href="<c:url value="/url">	           
	            </c:url>">Home</a>
	    <%}%>
	
	    <h2>Sign Up</h2>
	
	    <% if ((Boolean)request.getAttribute("signUpFailed")){%>
	        <b>Sign Up failed. Please try again.</b><br /><br />
	    <%}%>
	
	    <form method="POST" action="<c:url value="/login" />">
			<div class="form-group">
				<input type="hidden" name="action" value="signup" class="form-control"/>
				Username<br />
				<input type="text" name="username" required class="form-control"/><br /><br />
				Password<br />
				<input type="password" name="password" required class="form-control"/><br /><br />
				Confirm Password<br />
				<input type="password" name="conpasswd" required class="form-control"/><br /><br />
				<input type="submit" value="Sign Up" class="btn btn-primary"/>
			</div>
	    </form>
	 </div>
</div>

</body>
</html>