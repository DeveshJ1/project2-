package project2;

public class Date implements Comparable<Date>{
	/*
	 * in this class I set up the date class which creates the date objects.
	 * I first set the private member variables and create the object using the constructor and throw the exceptions
	 * for the arguments taken in. Then I set it for this current date object. 
	 */
    private int year;
    private int month;
    private int day;
    public Date( int year, int month, int day){
        if(year<=0){
            throw new IllegalArgumentException("Invalid value for year");
        }
        if (month >12 || month<1) {
            throw new IllegalArgumentException("Invalid value for month");
        }
        if (month == 1 || month == 3 || month == 05 || month == 7 || month == 8 || month == 10 || month==12 ){
            if(day>31 || day==0){
                throw new IllegalArgumentException("Invalid value for day");
            }
        }else if(month == 4 || month == 6 ||month == 9 ||month == 11 ){
            if(day>30|| day==0){
                throw new IllegalArgumentException("Invalid value for day");
            }
        }
        boolean leap=false;
        if (year % 4 != 0) {
            leap=false;;
        } else if (year % 400 == 0) {
            leap= true;
        } else if (year % 100 == 0) {
             leap= false;
         } else 
           leap =true;
        
        if(leap==true && month==2 ){
            if(day>29 || day==0){
                throw new IllegalArgumentException("Invalid value for day");
            }
        }else if( leap==false && month==2){
            if( day>28 || day==0){
                throw new IllegalArgumentException("Invalid value for day");
            }
        }
            this.year=year;
            this.month=month;
            this.day=day;
    }
    
    public int getYear() {
    	return this.year;
    }
    @Override
    /*
     * compare the date of the current object and check year, month, day in order and just return the difference of each.
     */
    public int compareTo(Date d) {
    	if(this.year!=d.year){
            return this.year-d.year;
        }else if(this.month!=d.month){
            return this.month-d.month;
        }else if(this.day!=d.day){
            return this.day-d.day;
        }else
            return 0;
        
        
	}
    /*
     * equals method for the date obect. I first check if they come from the same memory address
     * and then checking if it is null or instance of this class. if not it returns false. After i turn the object
     * into a date object and compare the two based on year month and day only returning true if they identical.
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj)
			return true;
		if (obj == null)
			return false;
        if (!(obj instanceof Date))
			return false;
        Date other = (Date) obj;
		if (other.year==this.year && other.month == this.month && other.day==this.day){
            return true;
        }else{
		return false;
        }
    }
    @Override
    /*
     * i validate the month and day to check if under then 10 and i add a zero to the formatting of this return
     * and I return the year month and day in the required format.
     */
    public String toString(){
    	if (month<10) {
    		return String.format(year+"-0"+month+"-"+day);
    	}else if(month<10 && day<10) {
    		 return String.format(year+"-0"+month+"-0"+day);
    	}else if(day<10) {
    		return String.format(year+"-"+month+"-0"+day);
    	}else {	
        return String.format(year+"-"+month+"-"+day);
    }
    }

}