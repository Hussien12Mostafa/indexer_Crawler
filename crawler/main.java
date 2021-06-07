package mahm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class main {
	static String link="D:\\traincrawl\\";//add position will you save 5000 pages same link in class webcrawler
public static void 	main(String args[]) 
{

		int k=-1;
		File file = new File(link+"int.txt");
		try {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		  while ((st = br.readLine()) != null)
		    k=Integer.parseInt(st);
		  br.close();

		}
		catch(Exception x)
		{
			
		}
		String arrlinks[];
		arrlinks=new String[20];
		if(k==-1)
		{
			Scanner scan=new Scanner(System.in);
			
			System.out.println("Enter number of Thread (from 1 to 20)");
			int value=scan.nextInt();
			BufferedWriter  writer2 = null;
			
			 try {
				writer2 = new BufferedWriter( new FileWriter(link+"value.txt"));
				writer2.write(Integer.toString(value));
				writer2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			arrlinks[0]="https://www.nytimes.com";
			arrlinks[1]="https://www.nationalgeographic.com";
			arrlinks[2]="https://www.google.com";
			arrlinks[3]="https://www.youtube.com";
			arrlinks[4]="https://www.microsoft.com";
			arrlinks[5]="https://www.apple.com";
			arrlinks[6]="https://github.com";
			arrlinks[7]="https://www.geeksforgeeks.org";
				
			arrlinks[8]="https://stackoverflow.com";
			arrlinks[9]="https://www.wikipedia.org";
			arrlinks[10]="https://www.youm7.com";
			arrlinks[11]="https://www.beinsports.com";
			arrlinks[12]="https://www.javatpoint.com";
			arrlinks[13]="https://www.fatafeat.com";
			arrlinks[14]="https://www.spotify.com";
			arrlinks[15]="https://www.amazon.com";
			arrlinks[16]="https://edition.cnn.com";
			arrlinks[17]="https://www.adobe.com";
			arrlinks[18]="https://www.yahoo.com";
			arrlinks[19]="https://www.premierleague.com";

			webcrawler c1=new webcrawler(arrlinks,value,0);
			
			
		}
		else 
		{
			int value=-1;
			File file2 = new File(link+"value.txt");
			try {
				BufferedReader br2 = new BufferedReader(new FileReader(file2));
			value=Integer.parseInt(br2.readLine());
			}
			catch(Exception e) {
				
			}
			
            for(int i=0;i<k;i++)
            {
				File file1 = new File(link+"Thread"+i+".txt");
				try {
					BufferedReader br = new BufferedReader(new FileReader(file1));
					String st;
					  while ((st = br.readLine()) != null)
						  arrlinks[i]=st;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
				
            }
            webcrawler c1;
            if(value!=-1)
            c1=new webcrawler(arrlinks,value,k);
					
		}
	
	


	
}
}