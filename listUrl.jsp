<%@ page import="java.util.Map" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    @SuppressWarnings("unchecked")
    Map<String, String> urlDatabase =
            (Map<String, String>)request.getAttribute("urlDatabase");
%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>URL Shortener</title>
</head>
	<body>
		<a href="<c:url value="/login?logout" />">Logout</a>
		<h2>Your Shorten URLs:</h2>
		<%
		    if(urlDatabase.size() == 0)
		    {
		        %><i>There are no URLs been shorten.</i><%
		    }
		    else
		    {
		    	int counter = 1;
		        for(String name : urlDatabase.keySet())
		        {
		            String shortenURL = urlDatabase.get(name);
		            %>URL #<%= counter %>: <%= name %> 
		            is shorten to <a href="<c:url value="<%= name %>"></c:url>"><%= shortenURL %></a><br/>
		            
		            <%counter++;
		        }
		    }
		%>
	</body>
</html>