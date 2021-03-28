package courseDependency;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseCourseCatalog {
	static Map<String, ArrayList<String>> CourseAdjacencyList = new HashMap<String,ArrayList<String>>();
	static Map<String,Boolean> visited  = new HashMap<>();
	public static void parseCatalog(String fileName){
		Scanner scan = null;
		
		try {
			scan = new Scanner( new File(fileName));
			String currentLine;
	        String patternString = "CS[0-9][0-9][0-9]";
	        String prereqString = "Prerequisite";
	        String newStringString = "---";
	        String course = null;
	        //String c = null;
	        boolean newStringFound = false;
	        boolean prereqFound = false;
	        Pattern pattern = Pattern.compile(patternString);
	        Pattern newStringPattern = Pattern.compile(newStringString);
			while (scan.hasNextLine()) {
				currentLine = scan.nextLine();
				//System.out.println(currentLine);
		        Matcher matcher = pattern.matcher(currentLine);
		        Matcher newLineMatcher = newStringPattern.matcher(currentLine);
		        if (currentLine.contains(prereqString)){
		        	prereqFound = true;
		        	//System.out.println(prereqFound);
		        	continue;
		        }
		        if(newLineMatcher.find()){
		        	newStringFound = true;
		        }
	        	if(matcher.find()){
		            //System.out.println("Match found: " + matcher.group(0));
			        if(prereqFound==true){
			        	
			        	ArrayList<String>arr = new ArrayList<>();
			        	//System.out.println("Prereq found: " + matcher.group(0)+" prereqFound "+prereqFound);
			        	//String n = new String();
			        	//n.courseCode = matcher.group(0);
			        	arr.add(matcher.group(0));
			        	while(matcher.find()){
			        		//n = new String();
			        		//n.courseCode = matcher.group(0);
			        		/*
			        		if(n.children==null){
				        		n.children = new ArrayList<String>();
				        		n.children.add(c);
			        			
			        		}else{
			        			n.children.add(c);
			        		}
			        		*/
			        		arr.add(matcher.group(0));
				        	//System.out.println("Prereq found: " + matcher.group(0)+" prereqFound "+prereqFound);
			        	}
		        		CourseAdjacencyList.put(course, arr);
			        	
			            prereqFound=false;
			        }else if(newStringFound){
			        	course = matcher.group(0);
			            //System.out.println("String Found: " + String );
			        	//c  = new String();
			        	//c.courseCode = String;
			            CourseAdjacencyList.put(course, null);
			            newStringFound = false;
			        }
	        	}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			scan.close();
		}
		//return CourseAdjacencyList;

	}
	public static void searchPrereq(String course){
		ArrayList<String> prereqs = CourseAdjacencyList.get(course);
		System.out.print(course+" -->");
		if(prereqs==null){
			System.out.println();
			return;
		}
		visited.put(course, true);
		for(String prereq:prereqs){
			if(!visited.containsKey(prereq)){
				searchPrereq(prereq);
			}
		}
	}
	
	public static void main(String[]args){
		parseCatalog("C:/Sheetal/Research/Personalization/PersonalizedLearning/lib/CourseCatalog.txt");
		//Map<String, ArrayList<String>> CourseAdjacencyList = new HashMap<>();

		Graph g  = new Graph();
		//g.root = new String();
		//g.root.preRequisite = null;
	    g.courseAdjacencyList = CourseAdjacencyList;
	    searchPrereq("CS431");
/*
		//addString(String n,ArrayList<String> parent)
		for (Map.Entry<String, ArrayList<String>> entry : CourseAdjacencyList.entrySet()) {
		    String key = entry.getKey();
		    ArrayList<String> values = entry.getValue();
		    //g.addCourse(key,values);
		    
		    if(values!=null){
			    System.out.println("String: "+key.courseCode+" has Pre-requisites: ");
			    for(String value:values ){
			    	System.out.print(value.courseCode+", ");
			    }
		    	
		    }else{
		    	System.out.println("String: "+key.courseCode+" has no Pre-requisites");
		    }
		    System.out.println();
		    
		}
		//g.searchEntireGraph(g.root);
*/
	}

}
