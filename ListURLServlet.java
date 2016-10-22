package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(
        name = "listURLServlet",
        urlPatterns = {"/listURL"},
        loadOnStartup = 1
)
public class ListURLServlet extends HttpServlet
{
    private Map<String, String> urlDatabase = new LinkedHashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(request.getSession().getAttribute("username") == null)
        {
            response.sendRedirect("login");
            return;
        }
        
    	this.urlDatabase.put("www.google.com", "bit.ly/1");
    	this.urlDatabase.put("www.yahoo.com", "bit.ly/2");
    	this.urlDatabase.put("www.github.com", "bit.ly/3");
        
        request.setAttribute("urlDatabase", this.urlDatabase);
        
                request.getRequestDispatcher("/WEB-INF/jsp/view/listUrl.jsp")
                        .forward(request, response);
    }
}
