package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * I first accomplish all the validation of the file and then set up all the member variable for each dataset
 * then I create the data list loop through the file and parse through it obtaining the values for each
 * variable using the getmethod. Then I use these values to create each dataset using the constructor of three variables. 
 * Then I create the datasetlist and add each dataset to the dataset list. Then i issue the querie and validate the key words
 * and the querie entered. Then I used getter methods of the datasetlist to check which dataset contain the 
 * querie with the correct keyword. Then i print the final dataset list which corresponds to the  correct queries and keywords.
 */
public class DataIsPlural{
    public static void main(String [] args) throws FileNotFoundException, MalformedURLException{
        if(args.length==0){
            System.err.println("Usage Error: the program expects file name as an argument.\n");
            System.exit(1);
        }
        File dataFile = new File(args[0]); 
		if (!dataFile.exists()){
			System.err.println("Error: the file "+dataFile.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!dataFile.canRead()){
			System.err.println("Error: the file "+dataFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
		System.out.println("Welcome the Data Is Plural data explorer!\nYou can use the following queries to search through the data:\ntitle KEYWORD\ndescription KEYWORD\nurl KEYWORD\nYou can combine up to two queries to narrow down the results, for example\ntitle KEYWORD1  url KEYWORD2\nEnter query or \"quit\" to stop:" );
		String title=null;
		String description=null;
		String link=null;
		String date=null;
		String hatTips=null;
		CSV inputData = new CSV(new Scanner (dataFile ));
		inputData.getNextRow();
		DataSetList data_List= new DataSetList();
		//System.out.println(inputData.getNumOfRows());
		for (int i = 0; i < inputData.getNumOfRows()-1; i++ ) {
			try {
			ArrayList<String> row= new ArrayList<String>();
			ArrayList<URL> links= new ArrayList<URL>();
			row=inputData.getNextRow();
			date=row.get(0);
			title=row.get(2);
			//System.out.println(title);
			description=row.get(3);
			if(row.size()==6) {
			hatTips=row.get(5);
			}
			link=row.get(4);	
			//System.out.println(link);	
			String 	[] url_links= link.split("\\n");
			/*for(int j=0;j<url_links.length;j++){
			//	System.out.println(url_links[j]);
			}*/
			for(int j=0;j<url_links.length;j++) {
				//System.out.println(url_links[j]);
				URL myURL= new URL(url_links[j]);
				links.add(myURL);
				//System.out.println(myURL);
			}
			
			DataSet b= new DataSet(title,description,links);
			if (date!="") {
			String [] datee=date.split("[.]", 0);
			String yearr="";
			String monthh="";
			String dayy="";
			yearr=datee[0];
			monthh=datee[1];
			dayy=datee[2];
			int year=Integer.parseInt(yearr);
			int month=Integer.parseInt(monthh);
			int day= Integer.parseInt(dayy);
			Date d= new Date(year, month, day);
			b.setDate(d);
			}
			if(hatTips!="") {
					b.setHatTips(hatTips);
				}
			//System.out.println(b.getTitle() + b.getDescription() + b.getURL());
			//System.out.println(b.toString());
			data_List.add(b);
		}catch(IllegalArgumentException e){
			System.err.println(e.toString());
			continue;
		}
			} 
			//
			
			//System.out.println(data_List.toString());
			boolean flag=true;
			Scanner input= new Scanner(System.in);
			while(flag){
				try
				{
				System.out.println("Enter the querie and KEYWORD");
				String querie= input.nextLine();
				String[] qtr= querie.split("\\s+");
				//System.out.println(qtr.length);
				if(querie.toLowerCase().compareToIgnoreCase("quit".toLowerCase())==0){
					System.exit(0);
				}
				if(qtr.length>4){
					System.out.println("This is not a valid query. Try again.");
					continue;
				}
				if (qtr.length==2){
					if(querie.toLowerCase().contains("title")){
					String keyword= qtr[1];
					if(data_List.getByTitle(keyword)==null){
						System.out.println("No matches found. Try again");
						continue;
					}
					System.out.println(data_List.getByTitle(keyword).toString());
				}
					if(querie.toLowerCase().contains("description")){
					String keyword=qtr[1];
					if(data_List.getByDescription(keyword)==null){
						System.out.println("No matches found. Try again");
						continue;
					}
					System.out.println(data_List.getByDescription(keyword).toString());
				}
					if(querie.toLowerCase().contains("url")){
					String keyword= qtr[1];
					System.out.println(data_List.getByURL(keyword).toString());
				}
				}else if(qtr.length>2){
					if(querie.toLowerCase().contains("title") && querie.toLowerCase().contains("description")){
					String[] az= querie.split("\\s+");
					if(az[0].toLowerCase().contains("title")){
						String title_keyword=  az[1];
						String description_keyword= az[3];
						if(data_List.getByTitle(title_keyword)==null || data_List.getByDescription(description_keyword)==null){
							System.out.println("No matches found. Try again.");
						}
						System.out.println(data_List.getByTitle(title_keyword).getByDescription(description_keyword));
					}else{
						String title_keyword=az[3];
						String description_keyword=az[1];
						if(data_List.getByTitle(title_keyword)==null || data_List.getByDescription(description_keyword)==null){
							System.out.println("No matches found. Try again.");
						}
						System.out.println(data_List.getByTitle(title_keyword).getByDescription(description_keyword));
					}
				}else if(querie.toLowerCase().contains("title") && querie.toLowerCase().contains("url")){
					String[] az= querie.split("\\s+");
					if(az[0].toLowerCase().contains("title")){
						String title_keyword=az[1];
						String url_keyword=az[3];
						if(data_List.getByTitle(title_keyword)==null || data_List.getByURL(url_keyword)==null){
							System.out.println("No matches found. Try again.");
							continue;
						}
						System.out.println(data_List.getByTitle(title_keyword).getByURL(url_keyword));
					}else{
						String title_keyword=az[3];
						String url_keyword=az[1];
						if(data_List.getByTitle(title_keyword)==null || data_List.getByURL(url_keyword)==null){
							System.out.println("No matches found. Try again.");
							continue;
						}
						System.out.println(data_List.getByTitle(title_keyword).getByURL(url_keyword));
	
					}
				}else if(querie.toLowerCase().contains("description") && querie.toLowerCase().contains("url")){
					String[] az= querie.split("\\s+");
					if(az[0].toLowerCase().contains("description")){
						String description_keyword=az[1];
						String url_keyword=az[3];
						if(data_List.getByDescription(description_keyword)==null || data_List.getByURL(url_keyword)==null){
							System.out.println("No matches found. Try again.");
						}
						System.out.println(data_List.getByDescription(description_keyword).getByURL(url_keyword));
					}else{
						String description_keyword=az[3];
						String url_keyword=az[1];
						if(data_List.getByDescription(description_keyword)==null || data_List.getByURL(url_keyword)==null){
							System.out.println("No matches found. Try again.");
						}
						System.out.println(data_List.getByDescription(description_keyword).getByURL(url_keyword));
					}
					
				}
				}
				
				continue;
				}
				catch( IllegalArgumentException e) {
					System.err.println(e.toString());
					continue;
				}
			}
    }
}