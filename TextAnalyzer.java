import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class TextAnalyzer {

	
	static int nextIndex = 0;
	static String[] checkArray = new String[1100];
	
	
	
	
	public static void push(String e) {
	    checkArray[nextIndex] = e;
	    ++nextIndex;
	}
	
	public static int find(String target)
	{
	    for (int i = 0; i < 500; i++)
	    {

	        if (checkArray[i].equals(target)) {

	        	return i;
	            
	        }
	    }
	 
	    return 0;
	}
	
	public static void main(String[] args) {

		/*This first Section is to pull the webpage as an HTML file so that
		 * I can change it into a really long string*/
		
		String raven ="";
		
		try {
			String webPage = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String result = sb.toString();

			raven=result;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*This section pulls the specific part of the String that
		 * is the actual poem. The lines of code after help clean up
		 * and remove unwanted punctuation, HTML tags, and also
		 * weird symbols that appeared in place of certain punctuation
		 * due to transferring from the web through Java*/
		
		String ravena = raven.substring(3167,11430);
		

		
		ravena = ravena.replaceAll("<[^>]*>", "");
		ravena = ravena.replaceAll("’", "");
		ravena = ravena.replaceAll("&mdash;", " ");
		ravena = ravena.replaceAll(";", " ");
		ravena = ravena.replaceAll("“", "");
		ravena = ravena.replaceAll("�", "");

		ravena = ravena.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
		
		System.out.println(ravena);
		
		/*Here I split the string into an array with each element being a word from the poem*/
		
		String[] ravray = ravena.split(" ");

		/*I created an object array to use with a string array that I created globally
		 * to help organize how I wanted to move around data. One array kept the poem words, one kept objects that contained the count on each word, 
		 * and one last one was used to help check if the program had already run into a word
		 * so that it will know whether or not it should increase the count variable with 
		 * the objects within the object arrays*/
		
		Wordie[] wordArray = new Wordie[ravray.length];
		
		for (int i=0; i<=ravray.length-1; i++) {
			wordArray[i]= new Wordie("z");
		}

		for (int i=0; i<=ravray.length-1; i++) {

			if (Arrays.asList(checkArray).contains(ravray[i])) {
				wordArray[find(ravray[i])].upCount();

			}
			else {
				wordArray[nextIndex]=new Wordie(ravray[i]);
				push(ravray[i]);
			}
		}
		
		/*This last Section goes through the Object array that should only have one copy 
		 * of each word at this point and their number of appearances and uses 
		 * a "max" variable to write into one final array that has the Top 20 words in order*/
		
		System.out.println("\nThese are the words and their counts:\n");

		Wordie max= new Wordie("");
		
		Wordie[] topTw = new Wordie[20];
		for (int i=0; i<=19; i++) {
			for (int j=0; j<=nextIndex-1; j++) {
				if (wordArray[j].getCount()>max.getCount()) {
					max=new Wordie(wordArray[j].toString(), wordArray[j].getCount());
					
					wordArray[j].setZero();
					
				}
			}

			topTw[i]= new Wordie(max.toString(), max.getCount());
			max.setZero();
		}
		for (int i=0; i<=19; i++) {
			topTw[i].getCountS();
		}
		
		
	}
}
