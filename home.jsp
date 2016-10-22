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
		<title>Shortened URL Expander</title>
	</head>
	<body>
		<h2>Check Your Shortened URL!</h2> 
		<form method="POST" action=<c:url value="/url" />>
			Enter your short URL<br />
			<input type="text" name="shortUrl" /><br /><br />
			<input type="submit" value="Get Long URL" />
		</form>
		<%
		    if(urlDatabase.size() == 0)
		    {
		        %><i>There are no URLs in the system.</i><%
		    }
		    else
		    {
		        for(String longUrl : urlDatabase.keySet())
		        {
		            String url = urlDatabase.get(longUrl);
		            %>URL: <a href="<c:url value="<%= longUrl %>"></c:url>"><%= url %></a><br/>
		            <%
		        }
		    }
		%>
		<% String longUrl = request.getAttribute("longUrl") != null ? request.getAttribute("longUrl").toString() : "";
			if (!longUrl.isEmpty() && longUrl != "false") { %>
			<div>Long URL: <%= longUrl %></div>
			<% } %>

		<a href="login.jsp">Login</a><br />
		<a href="signup.jsp">Sign-up</a> 
	</body>
</html>