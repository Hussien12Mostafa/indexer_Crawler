package projectAP;

import java.io.*;

import javax.servlet.http.*;





public class MyMain extends HttpServlet{
	public static String[]arr;
	public static int k;
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			    throws IOException {
		 k=0;
			        String name = request.getParameter("userdata");
			        System.out.println(name);
			     read r=new read();
                     arr=r.mai(name);
                     System.out.println("arr len in mymain="+arr.length);
                    
                     String page = "<!doctype html> <html><head>  <link rel='stylesheet' href='/Results.css'></head> <body> ";
                    		 page+="<form id='SearchForm' method='GET' name='ProjectRequest4' action='ProjectRequest4'>\r\n"
         			        		+"    <button id='buthome'><img src='/GreenSearchIcon.png' id='SearchIcon'></button>\r\n"
         			        		
         			        		+ "</form>";
                    		 if(k+10<arr.length)
             			        page+="<form id='NextForm' method='GET' name='ProjectRequest2' action='ProjectRequest2'>\r\n"
             			        		+ "    <button id='butnext'><img src='/NextIcon.png' id='Next'></button>\r\n"
             			        		+ "</form>";
                     		page+= "<div id='SearchResults'>";
                     		
                 
     	    	
			        if(arr!=null)
			        {
			        System.out.println(arr.length+"i have url");
                   for(int i=0;i<arr.length;i++)
                    	{
       	    			
       	    		 String doc=r.getdoc(i);
       	    		 
       	    			if(doc!=null)
       	    			{   page+= "<div id='Link'><a href=' "+arr[i]+"'>" +doc+"</a><br><label>"+arr[i]+"</label> </div>" ;
			                	     
			                	     k=i;
			                	     if(i==9)
			                	    	 break;
       	    			}
    	    		
                    	}
                   k++;
			       
			       
			        }
			        else
			        	{
			        	page+="<h1>There are no results for "+name+"</h1>";
			        	System.out.println("i in else");
			        	}

			        page += "</div> </body></html>";
			        
				       
			         
			         MyMain1.k=k;
			         MyMain2.k=k;
                     response.setContentType("text/html");
			        response.getWriter().println(page);
			    }
public String[] geturls()
{
	return arr;
}
public int getk()
{
	return k;
}

}