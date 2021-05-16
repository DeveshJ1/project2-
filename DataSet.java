package project2;

import java.net.URL;
import java.util.ArrayList;

/*
 * This is where i create the dataset class first starting with creating the member variable and then 
 * the necessary dataset constructor validating the arguments and setting them. Then i establish the getter and setter methods
 * for the different member variable to make main program more efficient to run. Then I create a compare
 * method to compare the datasets when sorting them for the datasetlist. I also create a to string to print each 
 * dataset object and equals method to check the boolean. 
 */

public class DataSet implements Comparable<DataSet>{
	private String hatTips;
	private Date date;
	private String title;
	private String description;
	private ArrayList<URL> links;
	public DataSet (String title, String description, ArrayList<URL> links){
		if(title.equals("") || title==null){
			throw new IllegalArgumentException("Invalid title");
		}else if(description.equals("") || description==null){
			throw new IllegalArgumentException("Invalid description");
		}else if(links.isEmpty() || links == null){
			throw new IllegalArgumentException("Invalid list of URL links");
		}
		this.title=title;
		this.description=description;
		this.links=links;
	}
	public String getTitle(){
		return title;
	}
	public String getDescription(){
		return description;
	}
	public ArrayList<URL> getURL(){
		return links;
	}

	public void setDate(Date date){
		if(date==null || date.getYear()<2000){
			throw new IllegalArgumentException("Invalid Date");
		}else{
			this.date=date;
		}
	}
	public Date getDate(){
		if(date==null || date.getYear()<2000){
			throw new IllegalArgumentException("Invalid Date");
		}else{
		return date;
		}
	}
	public void setHatTips(String hatTips){
		if( hatTips==null){
			throw new IllegalArgumentException("Invalid hatTips");
		}else{
				this.hatTips= hatTips;
		}
	}
	public String getHatTips(){
		return this.hatTips;
	}
	@Override
	public int compareTo(DataSet d) {
		if (d==null) {
			return 1;
		}
		if(this.title==null || d.title==null)
		{
			return 1;
		}
		if(d.date==null || this.date==null) 
		{
			if(this.title.compareToIgnoreCase(d.title) == 0) 
			{
				return 0;
			}
			else 
			{
				return 1;
			}
		} 
		else if(this.date.equals(d.date) && this.title.compareToIgnoreCase(d.title) == 0)
		{	
				return 0;
		}
		
		
		return 1;
	}
	
	
	
	@Override
	public boolean equals(Object obj){
		 if (this == obj)
			return true;
		if (obj == null)
			return false;
			if (!(obj instanceof DataSet))
			return false;
        DataSet other = (DataSet) obj;
        
		if(other.date!=null && other.date.equals(this.date) && other.title.equalsIgnoreCase(this.title)){
			return true;
        }
		return false;
	}
	@Override
    public String toString() {
		String z="";
		for(int i=0;i<links.size();i++){
			z= z + links.get(i) + "\n";
		}
		if(date!=null){
			String retstr= date + "\n" + title + "\n" + description + "\n" + z + "\n" ;
			return retstr;
		}else {
			String retstr= title + "\n" + description + "\n" + z + "\n";
			return retstr;
		}
		}
	}