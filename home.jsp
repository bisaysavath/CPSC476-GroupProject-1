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
		<h2>Check Your Shortened URL!</h2> 
		<form method="POST" action="<c:url value="/url" />">
			Enter your short URL<br />
			<div class="form-group">
				<input type="text" name="shortUrl" class="form-control" />
			</div>
			<input type="submit" value="Get long URL" class="btn btn-info" />
		</form>
		<%
		    if(urlDatabase.size() == 0)
		    {
		        %><i>There are no URLs in the system.</i><br /><%
		    }
		    else
		    {
		        for(String longUrl : urlDatabase.keySet())
		        {
		            String url = urlDatabase.get(longUrl); 
		            %>Long URL: <a href="<c:url value="<%= longUrl %>"></c:url>"><%= url %></a><br/>
		            <%
		        }
		    }
		%>

		<a href="<c:url value="/login">
    		<c:param name="action" value="login"/></c:url>">Login
    	</a>|
		<a href="<c:url value="/signup">
    		<c:param name="action" value="signup"/></c:url>">Sign Up
    	</a> 
		</div>
	</body>
</html>