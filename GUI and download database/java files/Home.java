package projectAP;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Home extends HttpServlet{

	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			    throws IOException {
		
			       System.out.println("Hi i in Home");
			     
                     String page = "<!DOCTYPE html>\r\n"
                     		+ "<html>\r\n"
                     		+ "\r\n"
                     		+ "    <head>\r\n"
                     		+ "        <link rel=\"stylesheet\" href=\"/ProjectAP.css\">\r\n"
                     		+ "    </head>\r\n"
                     		+ "\r\n"
                     		+ "    <body>\r\n"
                     		+ "\r\n"
                     		+ "                            <!-- Searchbar DIV -->\r\n"
                     		+ "        <div class=\"SearchDIV\">\r\n"
                     		+ "            <form method=\"GET\" name=\"Projec\" action=\"ProjectRequest\"> \r\n"
                     		+ "                <input name=\"userdata\" placeholder=\"Search\" required> \r\n"
                     		+ "                <button>\r\n"
                     		+ "                    <img src=\"/Search.png\">\r\n"
                     		+ "                </button>\r\n"
                     		+ "            </form>\r\n"
                     		+ "        </div>\r\n"
                     		+ "        \r\n"
                     		+ "    </body>\r\n"
                     		+ "</html>";
			
			      
			        response.setContentType("text/html");
				       
			         

			        response.getWriter().println(page);
			    }

}