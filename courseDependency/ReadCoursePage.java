package courseDependency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Sheetal
 *
 */
public class ReadCoursePage {
	ArrayList<URL> urlList = new ArrayList<URL>();

	public static void readPage(URL url){
		try {
			//url = new URL("http://www.oracle.com/");
	        BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            	parseURL(inputLine);
            in.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void parseURL(String inputLine){
        String patternString = "(/search/?P=)(.*\")";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputLine);

        int count = 0;
        while(matcher.find()) {
            count++;
            System.out.println("found: " + count + " : "+ matcher.group(1)+matcher.group(2));
        }

	}
	public static void main(String[]args){
		try {
			URL url = new URL("http://catalog.njit.edu/undergraduate/computing-sciences/computer-science/bs/");
			readPage(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
