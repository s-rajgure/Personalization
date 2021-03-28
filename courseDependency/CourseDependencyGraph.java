package courseDependency;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CourseDependencyGraph {
	
	public static void getSearchLinks(String url){
        Document doc;
        ArrayList<String> courseLinks = new ArrayList<String>();
		try {
			doc = Jsoup.connect(url).get();
	        Elements links = doc.select("a[href]");
	        for (Element link : links) {
	        	
	        	String stLink = link.attr("abs:href").toString();
	        	if(stLink.contains("/search")){
	        		courseLinks.add(stLink);
	        	}
	            //System.out.println(stLink);
	        }
	        for(String link:courseLinks){
	        	System.out.println(link);
	        }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void getContent(String url){
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
	        Elements links = doc.select("a[href]");
	        for (Element link : links) {
	        	
	        	String stLink = link.text();
	            System.out.println(stLink);
	        }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args){
		//getSearchLinks("http://catalog.njit.edu/undergraduate/computing-sciences/computer-science/bs/");
		getContent("http://catalog.njit.edu/search/?P=MATH 111");
	}

}
