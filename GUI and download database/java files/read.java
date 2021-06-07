package projectAP;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class read {

	
	public static HashMap<String,HashMap<Integer , Integer>> TF  = new HashMap<String,HashMap<Integer,Integer>>();
	public static HashMap<String ,Integer> DF = new HashMap<String , Integer>();
	public static HashMap<String , ArrayList<Integer>>inverse = new HashMap<String , ArrayList<Integer>>();
	public static ArrayList<HashMap<String , Integer>> myMap  = new ArrayList<HashMap<String,Integer>>();
	public static String link="D:\\databaseProjectAP\\"; //for download database(TF,DF,inverse,myMab)
	public static String link1="D:\\databaseProjectAP\\";//for download Links(URL)
	public static String link2="D:\\proj\\";//for open document
	public static boolean temb=true;
public static ArrayList<Integer>arr5;
public static String[] mai(String g) throws IOException
{
	if(temb)
	{
	 File file=new File(link+"inverse.txt");
     Scanner scan=new Scanner(file);	
     
     String word="";
     String arr="";
     String[] arrr;
     word= scan.nextLine();
     while(scan.hasNextLine())
     {
    	ArrayList<Integer> intar=new ArrayList<Integer>();
    	word= scan.nextLine();
    	arr=scan.nextLine();
    	arrr=arr.split(" ");
    	while(!arrr[0].equals("-1"))
    	{

    	for(int i=0;i<arrr.length;i++)
    	{
    		intar.add(Integer.parseInt(arrr[i]));
    	}
    	if(scan.hasNextLine())
    	{
    	arr=scan.nextLine();
    	arrr=arr.split(" ");
    	}
    	else
    		break;
    	}
    	inverse.put(word, intar);
     }
     
     System.out.println("downloaded inverse");
     
     file=new File(link+"DF.txt");
     scan=new Scanner(file);	
     
     word="";
     String []intg;
     int ing=0;
     while(scan.hasNextLine())
     {
    	word= scan.nextLine();
    	intg=word.split(" ");
    	ing=Integer.parseInt(intg[1]);
    	DF.put(intg[0],ing);
     }
     
     System.out.println("downloaded DF");

     
     
	 file=new File(link+"mymap.txt");
     scan=new Scanner(file);	
     
     word="";
     String [] words;
     
     scan.nextLine();
     while(scan.hasNextLine())
     { 
    	word= scan.nextLine();
    	
    	HashMap<String,Integer> jj=new HashMap<String,Integer>();
    	word=scan.nextLine();
    	
    	while ( !word.equals("M"))
    	{
    		words=word.split(" ");
    		jj.put(words[0],Integer.parseInt(words[1]));
    		
    		if(scan.hasNextLine())
    		word=scan.nextLine();
    		else
    		break;
    	}
    	myMap.add(jj);
    	
     }
     System.out.println("downloaded myMab");

     
	 file=new File(link+"TF.txt");
     scan=new Scanner(file);	
     
     word="";
     String line="";
     scan.nextLine();
 
     while(scan.hasNextLine())
     { 
    	line= scan.nextLine();
    	word=line;
    	

    	line=scan.nextLine();

    	HashMap<Integer,Integer> jj=new HashMap<Integer,Integer>();    
    	while ( !line.equals("M"))
    	{
    		words=line.split(" ");
    		
    		jj.put(Integer.parseInt(words[0]),Integer.parseInt(words[1]));
    		
    		if(scan.hasNextLine())
    		line=scan.nextLine();
    		else
    		break;
    	}
    	TF.put(word,jj);
    	
     }


     }     
     System.out.println("end download database");
	String inp= g;
	String []input1=inp.split(" ");
	PorterStemmer ps = new PorterStemmer();

	for(int i=0;i<input1.length;i++)
	{
		input1[i]=ps.stemWord(input1[i]);
	}
	System.out.println("Input Data");
	for(int i=0;i<input1.length;i++)
	{
		System.out.println(input1[i]);
	}
	System.out.println("End Input Data");
	 int q=0;//deh zy count
	    //if the first input is not there we will wait for the second input
	    while(q<input1.length&&inverse.get(input1[q])==null)
	    {
	    	System.out.println("not found word  input.length="+input1.length+" q="+q);
	  	  q++;
	    }
	    System.out.println("end while ");
	    BufferedReader ay7aga = new BufferedReader (new FileReader (link1+"v.txt"));
	    
	    //if the input is correct or not
	    if(q<input1.length)
	    {
		          ArrayList<Integer> arr2=new ArrayList<Integer>(inverse.get(input1[q]));
		          System.out.println("Size of inverse:"+inverse.get(input1[q]).size());
		          System.out.println("Size of arr2:"+arr2.size());
			      for(int i=q+1;i<input1.length;i++)
			      {
			    	  if(inverse.get(input1[i])!=null)
			    	  arr2.retainAll(inverse.get(input1[i]));
			      }
			     arr5=arr2;
			      String ss="";
			      int pg=0;
			      int coun=0;
			      String []willsend=new String[arr2.size()];
			      for(int i = 0;i<arr2.size();i++)
			      {
			    	  System.out.println(pg=arr2.get(i));
			    	System.out.println("before count :"+coun);
			    	  while(coun<pg)
				      {
			    		  
				    	coun++;
				    	ay7aga.readLine();
						
				      }
			    	  System.out.println("after count :"+coun);
					ss=ay7aga.readLine();
					coun++;
					willsend[i]=ss;
					System.out.println(ss);
			      }		
			      
			      coun=0;
			      q=0;
			      pg=0;
			      ay7aga.close();
			      return willsend;
	}
	    
	    q=0;
	    ay7aga.close();
	    return null; 
	}
public  String getdoc(int i)
{
	System.out.println("size arr5="+arr5.size());
	String g="";
String sear="</title>";
	
	File filee=new File(link2+arr5.get(i)+".txt");
    try {
		Scanner scan=new Scanner(filee);
		while(scan.hasNextLine())
		{
			g=scan.nextLine();
			
			if(g.contains(sear))
			{
				
				break;
			}
		}
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	
    
    if(g!="")
    {
String needit="";
boolean x=true;
int b,w;
for(int j=1;j<g.length();j++)
{
	
	if(g.charAt(j)=='>')
	{
		for(int l=j+1;l<g.length();l++)
			{
			
			if(g.charAt(l)!='<')
			{
				needit+=g.charAt(l);
			}
			else
				{
				x=false;
				break;
				}
			}
	}
	if(x==false)
	{
		break;
	}
}


return needit;
    }
    return null;
}
}
     
     






