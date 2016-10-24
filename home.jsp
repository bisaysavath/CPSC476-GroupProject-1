<%@ page import="java.util.Map, com.wrox.UrlShortener" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	@SuppressWarnings("unchecked")
	Map<String, String> urlDatabase =
		(Map<String, String>)request.getAttribute("urlDatabase");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<title>Shortened URL Expander</title>
	</head>
	<body>
		<div class="container-fluid">
		<a href="<c:url value="/login">
    		<c:param name="action" value="login"/></c:url>">Login
    	</a>|
		<a href="<c:url value="/signup">
    		<c:param name="action" value="signup"/></c:url>">Sign Up
    	</a> 
		<h2>Check Your Shortened URL!</h2> 
		<form method="GET" action="<c:url value="/url" />">
			<div class="form-group">
				Your long URL:<br />
				<div class="col-sm-10">
					<input type="text" name="shortUrl" class="form-control" placeholder="Enter short URL">
				</div>
				<input type="submit" value="Get long URL" class="btn btn-info">
			</div>
		</form>
		<%
			String url = request.getParameter("shortUrl");
			String longURL = urlDatabase.get(url);
		
		    if (urlDatabase.containsKey(url) == true)
		    {
		        %>Your Long URL: <%= longURL %><br/>
		        <%
		    }
		    else
		    {
		        %>There is no long URL for the short URL entered.<br /><%
		    }
		%>
		</div>
	</body>
</html>