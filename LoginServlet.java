package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by RD on 10/12/2016.
 */
@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {

    private static Map<String, String> userDatabase = new Hashtable<>();

    static{
        userDatabase.put("rakesh","rakesh");
        userDatabase.put("billy","billy");
        userDatabase.put("sumana","sumana");
        userDatabase.put("doris","doris");
        userDatabase.put("yui","yui");
    }

    //GET method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        //logout
        if (request.getParameter("logout") != null){

            System.out.println("In  /login doGet logout section");
            session.invalidate();
            request.getRequestDispatcher("/WEB-INF/jsp/view/logoutConfirm.jsp").forward(request,response);
            return;

        }
        //login
        else if (request.getParameter("action").equals("login") || session == null){

            System.out.println("In  /login doGet login section");
            request.setAttribute("loginFailed", false);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request,response);
            return;
        }

        else if (session.getAttribute("username") != null){

            if (session.getAttribute("userDatabase") != null){
                userDatabase = (Map<String,String>)session.getAttribute("userDatabase");
            }
            response.sendRedirect("url");
            return;
        }
        else if (session.getAttribute("username") == null){

            response.sendRedirect("/WEB-INF/jsp/view/login.jsp");
            return;
        }

        System.out.println("In  login doGet");
        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request,response);
        return;

    }
    //POST method
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        if (action == null){
            action = "login";
        }

        if (action.equals("logout")){

            System.out.println("In  login doPost logout section");
            request.setAttribute("loginFailed",false);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request,response);
            return;
        }
        //signup
        else if (action.equals("newUser")){

            System.out.println("In  login doPost action=newUser section");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String conpasswd = request.getParameter("conpasswd");

            if (LoginServlet.userDatabase.containsKey(username) || username == null ||
                    password == null || conpasswd == null || !password.equals(conpasswd)){

                request.setAttribute("signUpFailed", true);
                session.setAttribute("signUpFailed", true);
                request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp").forward(request,response);
                return;
            }
            else{
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                this.newUser(request, response);
            }
        }
        //login
        else{

            if (session.getAttribute("username") != null){
                response.sendRedirect("url");
                return;
            }
            else{

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (username == null || password == null ||
                        !LoginServlet.userDatabase.containsKey(username) ||
                        !password.equals(LoginServlet.userDatabase.get(username))){

                    request.setAttribute("loginFailed", true);
                    request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request,response);
                    return;
                }
                else{
                    session.setAttribute("username", username);
                    request.changeSessionId();
                    response.sendRedirect("url");
                }
            }
        }
    }

    private void newUser(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{

        if (request.getSession().getAttribute("username") == null){
            response.sendRedirect("login");
            return;
        }
        System.out.println("In  /login newUser method");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        this.userDatabase.put(username, password);
        System.out.println("Hello UserDatabase:"+userDatabase.keySet());
        response.sendRedirect("url");
    }

}
