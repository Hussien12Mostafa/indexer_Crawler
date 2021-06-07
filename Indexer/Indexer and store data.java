package projectAP;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class remove_best {
public static HashMap<String,HashMap<Integer , Integer>> TF  = new HashMap<String,HashMap<Integer,Integer>>();
public static HashMap<String ,Integer> DF = new HashMap<String , Integer>();
public static HashMap<String , ArrayList<Integer>>inverse = new HashMap<String , ArrayList<Integer>>();
public static ArrayList<HashMap<String , Integer>> myMap  = new ArrayList<HashMap<String,Integer>>();

public static int countpages=0;
public static boolean temb=true; 
public static final int numOfpages=5000;
public static String link="D:\\proj\\vistedLinks.txt";
public static String link1="D:\\proj\\";
public static String link2="D:\\databaseProjectAP\\";
public static void  main (String[] g)  throws IOException{
//	public static String[]  mai(String g)  throws IOException{

//	String inp= g;
//	String []input1=inp.split(" ");

	PorterStemmer ps = new PorterStemmer();



	indexer();
	fill();
	store();

	
   
  
//    return null;
}
	
public static void indexer()  throws FileNotFoundException{

String str=" ";
     for(int i=0;i<numOfpages;i++)
	      {
	    	  str=" ";
			      File file=new File(link1+countpages+".txt");
			      
			      Scanner scan=new Scanner(file);
			      while(scan.hasNextLine())
			      {
			    	  str+=scan.nextLine();
			      }
			      str = str.replaceAll("\\<.*?\\>", " ");
			      str = str.replaceAll("(?m)^[ \t]*\r?\n", " ");
			      str = str.replaceAll("[^a-zA-Z0-9]", " ");
			     
			      myMap.add(i,countin (str));
			      System.out.println("Doc="+i+" added to myMap");
			      str=" ";
			      countpages++;
	      }
	

	   }



	public static HashMap<String, Integer> countin (String strn) {

		  PorterStemmer ps = new PorterStemmer();
			  	
		String [] strin = strn.split(" ");
		Map<String, Integer> words = new HashMap<String, Integer>();
		HashMap<String, Integer> lastwords = new HashMap<String, Integer>();
		for(int i=0;i<strin.length ;i++)
		{
			int[]arr=new int[1];
			strin[i]=ps.stemWord(strin[i]);
			if(strin[i].length()>2&&strin[i].length()<12&&strin[i]!="the"&&strin[i]!="The") {
			if (words == null || words.get   (strin[i]) == null) {
	              words.put(strin[i], 1);
	          } 
			else {
	              words.put(strin[i], ((int) words.get(strin[i])) + 1);
	               
	          }	
			
		}
		}
	
		for (String i : words.keySet()) 
		{
			if(words.get(i)>2)
			{
					//System.out.println("word = "+i+" repeated"+words.get(i));
				if(inverse==null||inverse.get(i)==null)
				{
					
					 ArrayList arr1=new ArrayList();
					 arr1.add(countpages);
					 inverse.put(i, arr1);
				}
				else
					inverse.get(i).add(countpages);
				lastwords.put(i, words.get(i));
			}
            
			
		}
		words.clear();
		return lastwords;
	}
	public static void fill () 
	{

		for (String i : inverse.keySet()) 
		{
			DF.put(i,inverse.get(i).size());
		}
		
		for(int i=0;i<myMap.size();i++)
		{
			for (String j : myMap.get(i).keySet()) 
			{
				if(TF==null||TF.get(j)==null)
				{
				TF.put(j,new HashMap<Integer,Integer>());
				}
				TF.get(j).put(i, myMap.get(i).get(j));
			}
		}
	}
	
	
	
	public static void store () throws IOException 
	{
		//////storing inverse first
		BufferedWriter  writer3 = null;

		writer3 = new BufferedWriter( new FileWriter(link2+"inverse.txt",true));
		for (String i : inverse.keySet()) 
		{
			writer3.write(Integer.toString(-1));
			writer3.newLine();
			writer3.write(i);
			writer3.newLine();
			for(int m=0;m<inverse.get(i).size();m++)
			{
				writer3.write(Integer.toString(inverse.get(i).get(m))+ " ");
				
			}
			writer3.newLine();
		}
		writer3.close();
		
		
		//storing my map
		writer3 = new BufferedWriter( new FileWriter(link2+"mymap.txt",true));
		for(int i=0;i<myMap.size();i++)
		{
			writer3.write("M");
			writer3.newLine();
			writer3.write(Integer.toString(i));
			writer3.newLine();
			for (String j : myMap.get(i).keySet()) 
			{
				writer3.write(j+" "+Integer.toString(myMap.get(i).get(j)));
				writer3.newLine();
			}
		}
		writer3.close();
		
		
		//storing tf
		writer3 = new BufferedWriter( new FileWriter(link2+"TF.txt",true));
		for (String i : TF.keySet()) 
		{
			writer3.write("M");
			writer3.newLine();
			writer3.write(i);
			writer3.newLine();
			for(int m : TF.get(i).keySet())
			{
				writer3.write(Integer.toString(m)+ " "+Integer.toString(TF.get(i).get(m)));				
				writer3.newLine();
			}
		}
		writer3.close();
		
		//storing df
		writer3 = new BufferedWriter( new FileWriter(link2+"DF.txt",true));
		for (String i : DF.keySet()) 
		{
			writer3.write(i+" "+DF.get(i));
			writer3.newLine();
		}
		writer3.close();
		
}

		
}







