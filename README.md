# project2-
data_structure practice

In this project you will be working with open data. Wikipedia has a good description of open data: "Open data is the idea that some data should be freely available to everyone to use and republish as they wish, without restrictions from copyright, patents or other mechanisms of control." In fact, you will be working with an open data set that is a collection of open data sets. Data is Plural is a project run by Jeremy Singer-Vine. You can subscribe to his newsletter and get a fresh collection of interesting data sets each week. Or, you can browse the archives dating back to 2015.

Your program will explore this data set and allow the user to make queries about it.


The goal of this programming project is for you to master (or at least get practice on) the following tasks:

working with multi-file programs
reading data from input files
using and understanding command line arguments
working with large data sets
using the ArrayList class
writing classes
working with existing code
extending existing classes (inheritance)
parsing data
working with exception handling
PROGRAM USAGE
The program should run in a loop that allows the user to issue different queries. The three types of queries are:

title KEYWORD

description KEYWORD

url KEYWORD

In the above, the words in uppercase indicate keywords that will be replaced by actual values, for example title COVID, description immigration or url .gov.

In the first case, title KEYWORD, the program should display the entries from the data set whose titles (headlines) contain the KEYWORD specified by the user.

In the second case, description KEYWORD, the program should display the entries from the data set whose descriptions (text) contain the KEYWORD specified by the user.

In the third case, url KEYWORD, the program should display the entries from the data set whose links contain the KEYWORD specified by the user. For example, url .gov would return the data sets that are hosted on government sites.



The user should be able to combine two of the above queries (but not three or more) to narrow down the returned results. For example, if the user enters title COVID url .gov, the program should display the entries from the data set whose titles contain the word COVID and for which the links lead to sites with .gov in their URLs.



The keywords should be case insensitive. This means that title COVID, title covid and title Covid should all return exactly the same results.



On each iteration, the user should be prompted to enter a new query (for which the program computes the results) or the word 'quit' to indicate the termination of the program.

The user should not be prompted for any other response.

If the query entered by the user is invalid, the program should display an error message:

This is not a valid query. Try again.

and allow the user to provide an alternative query.

If the query entered by the user does not return any results, the program should print a message

No matches found. Try again.

and allow the user to provide an alternative query.



All output from the user interaction should be printed to the standard output stream (System.out).



Matching results display: If the query entered by the user matches one or more DataSet, the information should be displayed in the following format:

DATE

NAME

DESCRIPTION

LINKS

-----
All the words in uppercase letters are place-holders for the actual values from the data set.

If date is missing from the data set, it should be skipped in the result.

The output should contain a single blank line, followed by a line with five dashes, -----, after each result. This will visually separate the results for improved readability.

DATA STORAGE/ORGANIZATION

You need to provide an implementation of several classes that store the data and compute the results when the program is executed.

In particular, your program must implement and use the following classes. You may implement additional classes and additional methods in the required classes, if you wish.

Date class

This class should represent a calendar date. It should store the information about the year, month and day.

This class should provide a three parameter constructor that validates and sets the year, month and day.

public Date( int year, int month, int day)

A valid year has to be a positive integer. A valid month has to be a positive integer in the range of 1-12. A valid day depends on the month of the year and the year. Please see the Julian and Gregorian calendars for the length of the months and Leap Year Algorithm to determine how to calculate which year is a leap year. If this constructor is called with invalid arguments, it should throw an instance of the IllegalArgumentException with an appropriate message.

The class should implement Comparable<Date> interface. The two Date objects should be compared based on year, month and day (in that order). This way the earlier dates are smaller than the later dates.

The class should override the equals method from the Object class. Two Date objects are the same if they represent identical dates.

The class should override the toString method from the Object class. It should return a String matching the following pattern: YYYY-MM-DD.

DataSet class

This class should represent a data set with all of its characteristics (date, title/headline, description/text, list of links, and any other information you may decide may be useful to store).

This class should provide a three parameter constructor that validates and sets the title, description and a list of links.

public DataSet (String title, String description, ArrayList<URL> links)

Any non-empty string is a valid title or description. If this constructor is called with a null or an empty title or description, it should throw an instance of 

IllegalArgumentException with an appropriate message.

links should be a non-empty list of URL objects. Note that URL is a class defined in Java (you do not need to define it yourself). If this constructor is called with a null or an empty list as links, it should throw an instance of the IllegalArgumentException with an appropriate message.

There should be no default constructor.

This class should provide the following mutator and accessor methods. The mutator methods should validate the data according to the specification below. If any values are invalid, the method should throw an instance of IllegalArgumentException with an appropriate message.
public void setDate(Date date), public Date getDate() - a valid date should not be null and should have a year equal to or after the year 2000.
public void setHatTips( String hatTips), public String getHatTips() - a valid hatTip not null string (hat-tips are missing for many of the entries in the data set, so they could be and empty string; if the hat-tip was never set or it was set to an empty string, the getHatTips method should return an empty string)
The class should implement Comparable<DataSet> interface. The two DataSet objects should be compared based on their dates, and, if the dates are the same or missing (if the date is not set in either of the compared objects), by their titles. The comparison between titles should be case insensitive.

The class should override the equals method from the Object class. Two DataSet objects are the same if they have identical dates and titles (case of letters does not matter).

The class should override the toString method from the Object class. It should return a multi-line String matching the following pattern

  DATE
  
  NAME
  
  DESCRIPTION
  
  LINKS
  
All the words in uppercase letters are place-holders for the actual values from the data set.

If date is missing from the data set, it should be skipped in the result in the result. The description, in most cases, will be a long multi-line text. The links should be displayed one per line.

DataSetList class

The DataSetList class should be used to store all the DataSet objects. This class should inherit from the ArrayList<DataSet> class.

The class needs to provide a default constructor that creates an empty DataSetList object.

In addition, the class should implement the following two methods:

public DataSetList getByTitle ( String keyword )

This method should return a list of all DataSet objects for which the titles (headlines) contain the keyword. The keyword comparison should be done in a case insensitive way. The returned list should be sorted according to the natural ordering of the elements (i.e., dictated by the compareTo method defined in the DataSet class). If keyword is null or an empty string, this method should throw an instance of IllegalArgumentException with an appropriate message.

If there are no elements in the list that match the given criteria, this method should return null.

public DataSetList getByDescription ( String keyword )

This method should return a list of all DataSet objects for which the descriptions (text fields) contain the keyword. The keyword comparison should be done in a case insensitive way. The returned list should be sorted according to the natural ordering of the elements (i.e., dictated by the compareTo method defined in the DataSet class). If keyword is null or an empty string, this method should throw an instance of IllegalArgumentException with an appropriate message.

If there are no elements in the list that match the given criteria, this method should return null.

public DataSetList getByURL ( String keyword )

This method should return a list of all DataSet objects for which the links contain the keyword. The keyword comparison should be done in a case insensitive way. The returned list should be sorted according to the natural ordering of the elements (i.e., dictated by the compareTo method defined in the DataSet class). If keyword is null or an empty string, this method should throw an instance of IllegalArgumentException with an appropriate message.

If there are no elements in the list that match the given criteria, this method should return null.

DataIsPlural class

The DataIsPlural class is the actual program. This is the class that should contain the main method. It is responsible for opening and reading the data file, obtaining user input, performing some data validation and handling all errors that may occur (in particular, it should handle any exceptions thrown by your other classes and terminate gracefully, if need be, with a friendly error message presented to the user: The program should never just reprint the exception message as a way of handling an exception. These messages are rarely readable to the ordinary user and make it seem like the program crashed in response to the exception.

You may implement other methods in this class to modularize the design.
