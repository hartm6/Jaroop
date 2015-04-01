import java.util.Scanner;
import java.net.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Wikipedia class. 
 * This program will ask the user for a topic and then make a get request to wikipedia and print the first paragraph
 * or will print Not Found if the page was not found. 
 * This class uses a library called Jsoup to make the request and also parse the data. This library makes the request
 * and parsing of the results very easy and clean. 
 * My approach to this assignment was to deal with the user input in one method and then take that information and make
 * the request to the retrieve the Document. After retrieving the document I use Jsoup to access the information and select
 * what I want out of it.
 */
public class Wikipedia
{
  /**
   * getUserInput method
   * This method asks the user for a topic using the Scanner class built into Java.
   * The method takes the user's input and trims it with also making all characters lowercase. 
   * The method also removes all multiple spaces between words and puts a underscore between any two words. 
   */
  public String getUserInput()
  {
    Scanner userInput = new Scanner(System.in);
    System.out.println("Please enter a topic: ");
    String input = userInput.nextLine().toLowerCase();
    return input.trim().replaceAll(" +", "_");
  }
  /**
   * getParagraph method
   * Parameter: String 
   * This method initializes a few variables first. Then it uses a try/catch to perform the get request. 
   * The request is made calling the wikipedia url with appending the user's query to the end of it. 
   * The request will return a document, if the query was successfully performed then we use Jsoup to get the
   * first paragraph in the document. 
   * If the request failed from the user's input then I set the variable stuff to Not Found! 
   * Finally the method returns the first paragraph in the document or Not Found!
   */
  public String getParagraph(String userInputQuery)
  {
    Document doc = null;
    String stuff = ""; 
    try
    {
      doc = Jsoup.connect("http://en.wikipedia.org/wiki/" + userInputQuery).get();
      stuff = doc.select("p").get(0).text();
    }
    catch (IOException e)
    {
      stuff = "Not Found!";
    }
    catch (Exception e)
    {
      stuff = "Not Found!";
    }
    return stuff;
  }
    /**
   * Main method
   * Creates a Wikipedia class object and calls two methods of the class. 
   * One method is called and then returns the user's input and passes that string to the other method which 
   * makes the get request. The second method will return the paragraph. Finally the paragraph will be printed. 
   */
  public static void main(String[] args)  
  {
    Wikipedia wiki = new Wikipedia();
    if(args.length > 0)
      System.out.println(wiki.getParagraph(args[0]));
    else
      System.out.println(wiki.getParagraph(wiki.getUserInput()));
  }
}