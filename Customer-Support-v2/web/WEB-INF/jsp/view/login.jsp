<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Customer Support</title>
</head>
<body>
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<a href="<c:url value="/signup">
	            <c:param name = "action" value="signup"/>
	            </c:url>">Sign Up</a> |

		<%--  <a href="<c:url value="/signup">
              <c:param name="action" value="signup"/>
              </c:url>">Sign Up</a>|
             --%>
		<a href="<c:url value="/url">
	            </c:url>">Home</a>
		<h2> Login </h2>
		<%--You must login to access the customer support site <br /><br />--%>
		
		<% if((Boolean)request.getAttribute("loginFailed"))
		  { %>
		  		The username and password are incorrect. Please try again. <br /><br />
		<% } %>
		
		<form method="POST" action="<c:url value="/login" />" class="form-group">
			<h4>Enter UserName</h4> <br />
			<input type="text" name="username" class="form-control"/><br /><br />
			<h4>Enter Password</h4><br/>
			<input type="text" name="password" class="form-control"/><br /><br />
			<input type="submit" value="Login" class="btn btn-primary"/>
		
		</form>
	</div>
</div>


</body>
</html>