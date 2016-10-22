package edu.fullerton;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="PublicServlet",
			urlPatterns={"/public", "/public/*"})
public class PublicServlet extends HttpServlet {
       
    private Map<String, String> urlDatabase = new Hashtable<>();
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//This code is for step 5 of assignment
        urlDatabase.put("aaa123", "http://www.google.com");
        urlDatabase.put("bbb123", "http://www.fullerton.edu");
        urlDatabase.put("ccc123", "http://www.yahoo.com");
    	String requestedShortURL = request.getRequestURI();
    	requestedShortURL = requestedShortURL.substring(22);
    	System.out.println("request to short url: /" + requestedShortURL);
    	String foundURL = urlDatabase.get(requestedShortURL); //null if not found
    	System.out.println("redirecting to "+ foundURL);
    	if(foundURL != null) {
    	    //implementation to increase click count here
    	    response.sendRedirect(foundURL);
    	    return;
    	}
		
	}

}
