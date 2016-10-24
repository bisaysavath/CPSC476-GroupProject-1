package cpsc_476_Project1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@WebServlet(
        name = "loginServlet",
        urlPatterns = {"/login","/signup"}
)
public class LoginServlet extends HttpServlet {

    private static Map<String, String> userAccountDatabase = new Hashtable<>();

    static{
        userAccountDatabase.put("rakesh","rakesh");
        userAccountDatabase.put("billy","billy");
        userAccountDatabase.put("sumana","sumana");
        userAccountDatabase.put("doris","doris");
        userAccountDatabase.put("yui","yui");
    }

    //GET method
    @SuppressWarnings("unchecked")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
      
        //logout
        if (request.getParameter("logout") != null){

            System.out.println("In  /login doGet logout section");
            session.invalidate();
            request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(request,response);
            return;

        }
      
        //login
        else if (request.getParameter("action").equals("login")){

            System.out.println("In  /login doGet login section");
            request.setAttribute("loginFailed", false);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request,response);
            return;
        }
        //signup
        else if(request.getParameter("action").equals("signup")){

            System.out.println("In  /login doGet login section");
            request.setAttribute("signUpFailed", false);
            request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp").forward(request,response);
            return;
        }

        else if (session.getAttribute("username") != null){

            response.sendRedirect("url");
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
        else if (action.equals("signup")){

            System.out.println("In  login doPost action=newUser section");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String conpasswd = request.getParameter("conpasswd");

            if (LoginServlet.userAccountDatabase.containsKey(username) || username == null ||
                    password == null || conpasswd == null || !password.equals(conpasswd)){

                request.setAttribute("signUpFailed", true);
               // session.setAttribute("signUpFailed", true);
                request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp").forward(request,response);
                return;
            }
            else{
                session.setAttribute("username", username);
               // session.setAttribute("password", password);
                userAccountDatabase.put(username, password);
                System.out.println("Hello userAccountDatabase: "+userAccountDatabase.keySet());
      
                response.sendRedirect("url");
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
		                    !LoginServlet.userAccountDatabase.containsKey(username) ||
		                    !password.equals(LoginServlet.userAccountDatabase.get(username)))
		            {
		
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

  /*  private void newUser(String username, String password) throws  ServletException, IOException{

     /*   if (request.getSession().getAttribute("username") == null){
            response.sendRedirect("login");
            return;
        }
        System.out.println("In  /login newUser method");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
       
    }
	*/

}