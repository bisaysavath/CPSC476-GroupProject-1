<%@ page import="java.util.Map, cpsc_476_Project1.UrlShortener, cpsc_476_Project1.UrlDetails" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>URL Shortener</title>
</head>

<body>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
    
        <a href="<c:url value="/login">
				<c:param name="action" value="login"/></c:url>">Login
	  	</a>|
        <a href="<c:url value="/signup">
				<c:param name="action" value="signup"/></c:url>">Sign Up
 		</a>
        <h2>Check Your Shortened URL!</h2>
        <form method="POST" action="<c:url value=" /url " />">
            <div class="form-group">
                
                <br />
                <div class="col-sm-6">
                    <input type="text" name="shortUrl" class="form-control" placeholder="Enter short URL">
                </div>
                <input type="submit" value="Get long URL" class="btn btn-primary">
            </div>
        </form>
        <% 
        if(request.getAttribute("longurl") != null)
        { 
        	String longUrl = request.getAttribute("longurl").toString();
        	if(!longUrl.isEmpty())
            {
	        %>
	        <h3>Your Long URL:</h3><h4> <%=longUrl %></h4>
	                <br/>
	        <% 
	        } 
	        else
	        { 
	        %>
	         	<h4> There is no long URL for the short URL entered.</h4>
	              <br />
	              
	       <% 
	        } 
	    }
	    %>
    </div>
</div>
    
</body>

</html>
      