<%@page import="cpsc_476_Project1.UrlDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "java.util.Map" %>
 
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
<div><h2>Hello, <i><%=username %>!</i></h2></div>
<div>
<a href="<c:url value="/login?logout" />">Logout</a>
</div>

<%
	if(request.getAttribute("urlNull") != null)
	{ %>
	<h4>Please enter Url</h4>
	<%
	}
 %>
<form method="POST" action="<c:url value="/url" />">
	<h2>Enter Long URL:</h2><br />
	<div class="form-group">
		<input type="hidden" name="action" value="addUrl" class="form-control"/>
		<input type="text" name="longUrl" class="form-control" /><br />
		<input type="submit" value="Generate Shorten URL" class="btn btn-primary" />
	</div>
</form>
<hr>
  	
 <% 

  	if(request.getAttribute("innermap") != null) 
	{
  	%>
  
  <table style="width:100%" class="table table-striped table-hover">
  		<tr>
  			<th>Long Url</th>
  			<th>Short Url</th>
  			<th>Clicks</th>
  		</tr>
  <%  @SuppressWarnings("unchecked")
		Map<String, UrlDetails> userdb = (Map<String, UrlDetails>)request.getAttribute("innermap"); 
	
  		for(String eachKey : userdb.keySet())
  		{
   %>
  		<tr>
  			<td><%=userdb.get(eachKey).getLongUrl() %></td>
  			<td><a href= "<c:url value="<%=eachKey %>" />" onclick="window.location.reload(true)" target="newtab" > <%=eachKey %> </a></td>
  			<td><%=userdb.get(eachKey).getClicks() %></td>
  		</tr>
  <%
  		}
  	
   %>
  </table>
		<a class="btn btn-primary" href="<c:url value="/url">
	            <c:param name = "action" value="edit"/>
	            </c:url>">Edit</a>
  <%
  	}
  	else if(request.getAttribute("NoUrls") != null)  
  	{ 
  %>
  	<h4>You haven't entered any Url.<br />
  	We are waiting to show you when you enter </h4>
  <%
  	}
   %>
   </div>
   </div>
  
 </body>
 </html>
 

