package project2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("serial")
public class DataSetList extends ArrayList<DataSet> implements Comparator<DataSet>{
 /*
  * constructor for this class
  */
    public DataSetList(){
        
    }
    /*
     * then i create the getter methods. Each of these are very similar. I first validate the keywords
     * then I create a datasetList and run through a for loop to check each dataset. I split the string of title description and url
     * into a array of substring
     * then I check the title and validate it if equals null. Then i compare the string of the title/description/url to the keyword
     * and I validate the list sort it and return it.
     */
    public DataSetList getByTitle ( String keyword ){
    	if(keyword==null || keyword==""){
            throw new IllegalArgumentException("Invalid keyword");
        }
        DataSetList a= new DataSetList();
       // System.out.println(keyword);
        for(DataSet c: this){
            String title=c.getTitle();
            //System.out.println(title);
            if (title == null) 
				continue;
            //String [] sentence= title.split(" ");
            //for(String word: sentence){
                if(title.toLowerCase().contains(keyword.toLowerCase())){
                    a.add(c);
                    continue;
                }
            //} 
        }
		if(!a.isEmpty()){
			Collections.sort(a, new DataSetList());;
            return a; 
        }else{
            return null;
        }
        }
    
    public DataSetList getByDescription ( String keyword ){
        if(keyword==null || keyword==""){
            throw new IllegalArgumentException("Invalid keyword");
        }
        DataSetList a= new DataSetList();
        for(DataSet c: this){
            String description=c.getDescription();
            if (description == null) 
				continue; 
            //String [] sentence= description.split(" ");
            //for(String word : sentence){
                if(description.toLowerCase().contains(keyword.toLowerCase())){
                    a.add(c);
                    continue;
                }
            //}         
        }
        if(!a.isEmpty()){
            return a; 
        }else{
            return null;
        }
		

    }
    public DataSetList getByURL ( String keyword ) {
    if(keyword==null || keyword==""){
            throw new IllegalArgumentException("Invalid keyword");
        }
        DataSetList a= new DataSetList();
        for(DataSet c: this){
            ArrayList<URL> b=c.getURL();
            for(int i=0;i<b.size();i++){
                String z=b.get(i).toString();
            if(z.toLowerCase().contains(keyword.toLowerCase())){
                //System.out.println(z);
                a.add(c);
                break;
        }
            }
       
        }
        if(!a.isEmpty()){
            return a;
        }else{
            return null;
        }   
        }
	@Override
	public int compare(DataSet o1, DataSet o2) {
		return o1.compareTo(o2);
	}
    @Override
    public String toString(){
        for(int i=0;i<size();i++){
            System.out.println(get(i).toString());
        }
        return "";
    }
}