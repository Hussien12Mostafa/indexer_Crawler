package projectAP;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MyMain2 extends HttpServlet{
	public static String[]arr;
	public static int k;
	public static boolean temb=true;
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			    throws IOException {
                    
		 arr=MyMain1.arr;
		 String page = "<!doctype html> <html><head>  <link rel='stylesheet' href='/Results.css'></head> <body>";
				 page+="<form id='SearchForm' method='GET' name='ProjectRequest4' action='ProjectRequest4'>\r\n"
					 		+"    <button id='buthome'><img src='/GreenSearchIcon.png' id='SearchIcon'></button>\r\n"
					 		
					 		+ "</form>";
					 if(k-10<arr.length)
					     page+="<form id='NextForm' method=\"GET\" name=\"ProjectRequest2\" action=\"ProjectRequest2\">\r\n"
					     		+ "    <button id=\"butnext\"><img src='/NextIcon.png' id='Next'></button>\r\n"
					     		+ "</form>";
					 if(k-10>=10)
					 page+="<form id=\"PrevForm\" method=\"GET\" name=\"ProjectRequest3\" action=\"ProjectRequest3\">\r\n"
					 		+ "    <button id=\"butback\"><img id='ImgBack' src='/PrevIcon.png'></button>\r\n"
					 		+ "</form>";
				 page+= " <div id='SearchResults'>";

                     

			        if(arr!=null)
			        {
			        System.out.println(arr.length);
			  if(k%10!=0)
				  k=k-k%10;
                   for(int i=k-10;i<k;i++)
                    	{

                	   read r=new read();
                	   String doc=r.getdoc(i);
         	    		 System.out.println("got doc");
         	    			if(doc!=null)
         	    			{   page+= "<div id='Link'><a href=' "+arr[i]+"'>" +doc+"</a><br><label>"+arr[i]+"</label> </div>" ;
  			                	     
         	    			}
			                	     
	

                    	}
			

                  k=k-10;
			      
			        }
			        else
			        	{
			        		return;
			        	}
			      
			        page += "</div> </body></html>";
			        response.setContentType("text/html");

			         
			        MyMain1.k=k+10;
			        MyMain.k=k;
			        response.getWriter().println(page);
			    }


}