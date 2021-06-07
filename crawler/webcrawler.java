package mahm;


import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.FileWriter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
public class webcrawler implements Runnable {
private static final int MAX_DEPTH=3;

private String first_link;
private ArrayList<String>vistedLinks=new ArrayList<String>();
private static int ID;
private int IDnum;
private int numThread;
static int k;
private String link_first[];
int numpages;
static String linkpos="D:\\traincrawl\\";//add position will you save 5000 pages
int isvisted=0;
	public webcrawler(String link[],int numthread,int x) 
	{
		link_first=new String[link.length];
		link_first=link;
		numThread=numthread;
		numpages=5000;
	k=x;
	ID=-1;
		setthreads();
	}
	public void setthreads() 
	{
		ArrayList<Thread>arr=new ArrayList<Thread>();
		
		for(int i=0;i<numThread;i++)
		{
			System.out.println("WebCrawler created");
			Thread t;
		    t=new Thread(this);
		    t.setName(Integer.toString(i));
		    arr.add(t);
		}
		for(int i=0;i<numThread;i++)
		{
			arr.get(i).start();
		}
		for(int i=0;i<numThread;i++)
		{
			try {
				arr.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public  void run() {
		// TODO Auto-generated method stub
		synchronized(this) {
		System.out.println("URL : "+link_first[ID+1]+" ID : "+(ID+1));
		ID++;
		}
		crawl(1,link_first[ID]);

	}
	private void crawl(int level , String url ) 
	{
		ArrayList<String>disallowed=new ArrayList<String>();
		 disallowed=rob(url);
		if(k<numpages) {
		if(level<=MAX_DEPTH) 
		{
		Document doc=request(url);	
		
		if( doc != null) 
		{
			
			for(Element link : doc.select("a[href]") ) 
			{
				
				
				String next_link=link.absUrl("href");
				if(vistedLinks.contains(next_link)==false&&next_link!=null ) 
				{
					
					BufferedWriter  writer3 = null;
	    			 try {
	    				System.out.println("before "+linkpos+Thread.currentThread().getName()+".txt");
						writer3 = new BufferedWriter( new FileWriter(linkpos+"Thread"+Thread.currentThread().getName()+".txt"));
						System.out.println("after ");
		    			 writer3.write(next_link);
		    			 System.out.println("after writer");
		    			 writer3.close();
	    			 } catch (Exception e) {
						// TODO Auto-generated catch block
						return;
					}

	    			 if(disallowed.contains(next_link)==false) {
		 					crawl(level++,next_link);
		 }
					
				}
				
				
				
			}
		}
		
		}
		else
			return;
		}
		else
			return ;
	}
	private synchronized Document request (String url) 
{
	try(Stream<String> lines = Files.lines(Paths.get(linkpos+"v.txt")) ) {
	    if(lines.anyMatch(url::equals)) {
	    	System.out.println(url+" found");
	    	
	    	try 
	    	{
	    		Connection con =Jsoup.connect(url);
	    	
	    		
	    		Document doc=con.get();
	    		if(con.response().statusCode()==200) 
	    		{
	    			
	    			
	    			return null;
	    		}
	    		return null;
	    	}
	    	catch(Exception e) 
	    	{
	    		return null;
	    	}
	    		      
	    } 
	    else {
	        
	    	
	    	try 
	    	{
	    		Connection con =Jsoup.connect(url);
	    	
	    		
	    		Document doc=con.get();
	    		if(con.response().statusCode()==200) 
	    		{
	    			System.out.println("ID : "+Thread.currentThread().getName()+"Received webpage at : "+url);
	    			
	    			String title=doc.title();
	    			System.out.println(title);
	    			vistedLinks.add(url);
	    			
	    			
	    	    	

	    			
	    			
	    			
	    			
	    			
	    			
	    			
	    			BufferedWriter  writer2 = null;
	    			
	    			 writer2 = new BufferedWriter( new FileWriter(linkpos+"v.txt",true));
	    			 
	    	        try
	    	        {
	    	        	if(k<numpages) {
	    	           
	    	            writer2.write(url+"\n");
	    	        	}

	    	        }
	    	        catch ( Exception e)
	    	        {
	    	        	return null;
	    	        }
	    			
	    				writer2.close();		
	    			BufferedWriter  writer = null;
	    	        try
	    	        {
	    	        	
	    	           
	    	            if(k<numpages) {
	    	            	 writer = new BufferedWriter( new FileWriter(linkpos+k+".txt"));
	    	            writer.write(doc.toString());
	    	                      
	                     inc();
	                     BufferedWriter  writer4 = null;
	                     writer4 = new BufferedWriter( new FileWriter(linkpos+"int.txt"));
	                     writer4.write(Integer.toString(k));
	                     
	                     writer4.close();
	    	            
	    	        }
	    		}
	    	        catch ( Exception e)
	    	        {
	    	        	return null;
	    	        }
	    			
	    			
	    			
	    			
	    			isvisted=0;
	    			return doc;
	    		}
	    		return null;
	    	}
	    	catch(Exception e) 
	    	{
	    		return null;
	    	}
	    	
	    	
	    }
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		return null;
	}
	
	
	
}

public synchronized void inc() 
{

		k++;

	
	
	}
public static ArrayList<String> rob(String u) {
	 ArrayList<String>disallowed=new ArrayList<String>();
  try(BufferedReader in = new BufferedReader(
          new InputStreamReader(new URL(u+"/robots.txt").openStream()))) {
      String line = null;
      int flag=1;
      while((line = in.readLine()) != null) {
      	if(line.contains("User-agent: *")==true) {
      		flag=1;
      	}
      	if(line.contains("Dis") && flag==1)
          disallowed.add(u+line.substring(10));//line.substring(10)
      	
      	else if(line.contains("User-agent:"))
      	{
      		flag=0;
      	}
      }
  } catch (Exception e) {
      
  }
  return disallowed ;
}
}



