package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by RD on 10/14/2016.
 */
@WebServlet(
        name = "SignUpServlet",
        urlPatterns = "/signup"
)
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getParameter("action").equals("signup") && session.getAttribute("username") == null) {

            request.setAttribute("signUpFailed", false);
            request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp").forward(request, response);
            return;
        }
        else{
            response.sendRedirect("url");
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String conpasswd = request.getParameter("conpasswd");

        if(username == null || password == null || conpasswd == null || !password.equals(conpasswd)){

            request.setAttribute("signUpFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp").forward(request, response);
        }
        else{

            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("confirmPassword", conpasswd);
            session.setAttribute("action", "signup");
            response.sendRedirect("login");
        }

    }
}
