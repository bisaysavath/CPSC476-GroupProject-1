<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "cpsc_476_Project1.UrlDetails, java.util.Map" %>

<%
	String username = request.getSession().getAttribute("username").toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>URL Shortener</title>
</head>
<body>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div><h2>Hello, <i><%=username%>!</i></h2></div>
			<a href="<c:url value="/login?logout"></c:url>">Logout</a>
			 |
			<a href="<c:url value="/url"></c:url>">Home</a>
			<h2>URL Editing Page</h2><%
			if(request.getAttribute("innermap") != null)
			{
			%>
			<form method="POST" action="<c:url value="/url" />">
				<input type="hidden" name="action" value="removeUrl" class="form-control"/>
				<table style="width:100%" class="table table-striped table-hover">
					<tr>
						<th></th>
						<th>Long Url</th>
						<th>Short Url</th>
						<th>Clicks</th>
					</tr>
				<% @SuppressWarnings("unchecked")
					Map<String, UrlDetails> userdb = (Map<String, UrlDetails>)request.getAttribute("innermap");

					for(String eachKey : userdb.keySet())
					{
				%>
					<tr>
						<td><input type="checkbox" name="urlToRemove" value="<%= eachKey %>"></td>
						<td><%=userdb.get(eachKey).getLongUrl() %></td>
						<td><a href= "<c:url value="<%=eachKey %>" />" onclick="window.location.reload(true)" target="newtab" > <%=eachKey %> </a></td>
						<td><%=userdb.get(eachKey).getClicks() %></td>
					</tr>
				<%
					}%>
				</table>
				<input type="submit" value="Remove" class="btn btn-primary" />
			</form>
			<%
			}
			%>
		</div>
	</div>

</body>
</html>