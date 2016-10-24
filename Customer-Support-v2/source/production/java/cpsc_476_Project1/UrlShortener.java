package cpsc_476_Project1;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="UrlShortener",
			urlPatterns={"/url","/short/*"})

public class UrlShortener extends HttpServlet 
{
	private static final long serialVersionUID = 1L;       
    Map<String, UrlDetails> urlDatabase = new HashMap<>();    
    Map<String, Map<String, UrlDetails>> userDatabase = new HashMap<>();
        
    public UrlShortener() 
    {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	//Checks if user is not logged in
		if(request.getSession().getAttribute("username") == null)
        {
            request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(request, response);;
            return;
        }

		String username = request.getSession().getAttribute("username").toString();


		if(request.getParameter("action")!=null && request.getParameter("action").equals("edit"))
		{
			Map<String, UrlDetails> innermap = (Map<String, UrlDetails>) userDatabase.get(username);

			request.setAttribute("innermap", innermap);
            request.getRequestDispatcher("/WEB-INF/jsp/view/editUrl.jsp").forward(request,response);
            return;
		}
		String longUrlId = "";

		//When short Url is clicked
		if(request.getRequestURL().toString().contains("/short/"))
		{

			String shortUrlId = request.getRequestURL().toString();

			System.out.println(" request URI :" +shortUrlId);

			if(urlDatabase.containsKey(shortUrlId))
			{
				 UrlDetails urlDbValues= urlDatabase.get(shortUrlId);
				 longUrlId = urlDbValues.getLongUrl();
			}


			Map<String, UrlDetails> innermap = (Map<String, UrlDetails>) userDatabase.get(username);

			if(innermap != null)
			{
				UrlDetails updateClicks = innermap.get(shortUrlId);
				if(updateClicks != null)
				{
					updateClicks.setClicks(updateClicks.getClicks() + 1);
					innermap.put(shortUrlId, updateClicks);
					userDatabase.put(username, innermap);
				}
			}

		}

		if(!longUrlId.isEmpty())
		{
			System.out.println("Long Url = "+longUrlId);

			response.sendRedirect(longUrlId);
		    return;
		}

		//After login
		if(userDatabase.get(username) != null)
		{
			Map<String, UrlDetails> innermap = (Map<String, UrlDetails>) userDatabase.get(username);

			request.setAttribute("innermap", innermap);

		}
		else
		{
			request.setAttribute("NoUrls", true);

		}
		request.getRequestDispatcher("/WEB-INF/jsp/view/urlShortener.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null){
			action = "addUrl";
		}
		System.out.println("DoPost Called with action: " + action);
		switch (action)
		{
			case "removeUrl":
				removeUrl(request, response);
				break;
			case "addUrl":
			default:
				addUrl(request, response);
		}
	}

	private void removeUrl(HttpServletRequest request,
						   HttpServletResponse response)
				throws ServletException, IOException
	{
		String username = request.getSession().getAttribute("username") == null ? "" : request.getSession().getAttribute("username").toString();

		String [] urlToRemove = request.getParameterValues("urlToRemove");

		if (urlToRemove != null)
		{
			// Remove the list of URLs
			for (String s : urlToRemove)
			{
				if (userDatabase.get(username).containsKey(s))
				{
					System.out.println("Yes");
					userDatabase.get(username).remove(s);
				}
			}

			// If there no more URl, remove the user from the database
			if (userDatabase.get(username).size() == 0)
			{
				userDatabase.remove(username);
			}
		}

		if(userDatabase.get(username) != null)
		{
			Map<String, UrlDetails> innermap = (Map<String, UrlDetails>) userDatabase.get(username);

			request.setAttribute("innermap", innermap);
		}
		else
		{
			request.setAttribute("NoUrls", true);

		}
		request.getRequestDispatcher("/WEB-INF/jsp/view/urlShortener.jsp").forward(request, response);
	}

	private void addUrl(HttpServletRequest request,
					   HttpServletResponse response)
			throws ServletException, IOException
	{
		String longUrl = request.getParameter("longUrl");

		if(request.getParameter( "shortUrl")!=null)
		{
			String url = request.getParameter( "shortUrl");
			System.out.println(url);
			if(urlDatabase.containsKey(url))
			{
				UrlDetails urldetail=urlDatabase.get(url);
				request.setAttribute("longurl",urldetail.getLongUrl());
			}
			else
				request.setAttribute("longurl", "");
			request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(request, response);

		}

		String username = request.getSession().getAttribute("username") == null ? "" : request.getSession().getAttribute("username").toString();

		if( longUrl != null && !longUrl.isEmpty())
		{
			System.out.println("in If" +longUrl);
			try
			{
				//Checks if Entered long Url is valid URL(contains valid protocol like http,https; or not)
				//otherwise throws MalformedURLException

				new URL(longUrl);
			}
			catch (Exception e) {
				//if the url doesn't have valid protocol, below statement will append https to it

				longUrl = "https://"+longUrl;
			}

			String shortUrl = "";
			if(urlDatabase.size()>0)
			{
				for(String eachkey : urlDatabase.keySet())
				{
					UrlDetails eachvalue =  urlDatabase.get(eachkey);
					if(eachvalue.getLongUrl().equals(longUrl))
					{
						shortUrl = eachkey;
						break;
					}
				}
			}

			if(shortUrl.isEmpty())
			{
				String randomString = ShortURL.generateRandom();
				shortUrl = generateShortUrl(randomString, request);
				while(urlDatabase.containsKey(shortUrl))
				{
					String randomString1 = ShortURL.generateRandom();
					shortUrl = generateShortUrl(randomString1, request);
				}
			}

			//Inserting into userDatabase if its not already there
			//userDatabase will contain username when first url is entered
			if(!userDatabase.containsKey(username))
			{
				Map<String, UrlDetails> innermap = new HashMap<>();
				insertUserDatabase(innermap, username, shortUrl, longUrl);

			}
			else
			{
				Map<String, UrlDetails> innermap = (Map<String, UrlDetails>) userDatabase.get(username);
				System.out.println(shortUrl+" innermap has =  "+innermap.get(shortUrl)+" size of inner="+innermap.size());
				if(!innermap.containsKey(shortUrl))
				{
					insertUserDatabase(innermap, username, shortUrl, longUrl);
				}
			}

			//Inserting into urlDatabase if its not already there
			if(!urlDatabase.containsKey(shortUrl))
			{
				UrlDetails urldetail = new UrlDetails();
				urldetail.setLongUrl(longUrl);

				urldetail.addUserName(username);
				urlDatabase.put(shortUrl, urldetail);
			}

			response.sendRedirect("/url");
		}
		else
		{
			System.out.println("else"+ longUrl);
			request.setAttribute("urlNull", true);
			if(userDatabase.get(username) != null)
			{
				Map<String, UrlDetails> innermap = (Map<String, UrlDetails>) userDatabase.get(username);

				request.setAttribute("innermap", innermap);
			}
			else
			{
				request.setAttribute("NoUrls", true);

			}
			request.getRequestDispatcher("/WEB-INF/jsp/view/urlShortener.jsp").forward(request, response);

		}
	}
	
	public String generateShortUrl(String randomString,HttpServletRequest request)
	{
		String domainName[] = request.getRequestURL().toString().split("/", 5);		
		String shortUrl = domainName[0] +"//" + domainName[2] +"/short/"+ randomString;		
		return shortUrl;
	}
	
	public void insertUserDatabase(Map<String,UrlDetails> innermap, String username, 
			String shortUrl, String longUrl)
	{
		UrlDetails urlPojo = new UrlDetails();
		urlPojo.setLongUrl(longUrl);
		urlPojo.setClicks(0);
		innermap.put(shortUrl, urlPojo);
		
		userDatabase.put(username, innermap);
	}

}
