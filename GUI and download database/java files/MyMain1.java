package projectAP;

import java.io.*;

import javax.servlet.http.*;



public class MyMain1 extends HttpServlet{
	public static String[]arr;
	public static int k;
	public static boolean temb=true;
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			    throws IOException {
		 if(temb==true)
         {
         arr=MyMain.arr;
         k=MyMain.k;
         temb=false;
         }
System.out.println("i in MyMain1");
String page = "<!doctype html> <html><head>  <link rel='stylesheet' href='/Results.css'></head> <body> ";
page+="<form id='SearchForm' method='GET' name='ProjectRequest4' action='ProjectRequest4'>\r\n"
 		+"    <button id='buthome'><img src='/GreenSearchIcon.png' id='SearchIcon'></button>\r\n"
 		
 		+ "</form>";
 if(k+10<arr.length)
     page+="<form id='NextForm' method=\"GET\" name=\"ProjectRequest2\" action=\"ProjectRequest2\">\r\n"
     		+ "    <button id=\"butnext\"><img src='/NextIcon.png' id='Next'></button>\r\n"
     		+ "</form>";
 page+="<form id=\"PrevForm\" method=\"GET\" name=\"ProjectRequest3\" action=\"ProjectRequest3\">\r\n"
 		+ "    <button id=\"butback\"><img id='ImgBack' src='/PrevIcon.png'></button>\r\n"
 		+ "</form>";
		page+= "<div id='SearchResults'>";
                    
			        if(arr!=null)
			        {

			        
			        int y=k;
			        System.out.println("k="+k);
                   for(int i=k;i<arr.length;i++)
                    	{
                        read r=new read();
                	   String doc=r.getdoc(i);
         	    		 
         	    			if(doc!=null)
         	    			{   page+= "<div id='Link'><a href=' "+arr[i]+"'>" +doc+"</a><br><label>"+arr[i]+"</label> </div>" ;
  			                	     
         	    			}
   			                	     
			                	    y=i;
			                	     if(i==k+9)
			                	    	 {
			                	    	 
			                	    	 break;
			                	    	 }
                    	}
                   
                  k=y+1;
                  System.out.println("k="+k);
			        }
			        else
			        	{
			        		return;
			        	}
			        
			        
			        
			        
			        page += "</div> </body></html>";
			        response.setContentType("text/html");
				     
				      
			         
			         if(k%10==0)
			        MyMain2.k=k-10;
			         else
			        	 MyMain2.k=k; 
			        MyMain.k=k;
			        response.getWriter().println(page);
			    }


}