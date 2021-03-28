package graph;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class ReadKnowledgeBase {
	Node root = new Node();
	public void readOwlFile(String owlFile){
	     JSONParser parser = new JSONParser();
 	        try {
	 
	            Object obj = parser.parse(new FileReader(owlFile));
	 
	            //JSONObject jsonObject = (JSONObject) obj;
	            JSONArray JSONObject = (JSONArray) obj;	 
	            root.parent = null;
	            root.title = "root";
	            ArrayList<Node> toplist = new ArrayList<Node>();
	            root.children = toplist;
	            
	            readJSON(JSONObject, 1,root, toplist);
	            displayGraph(root);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}
	public void readJSON(JSONArray JSONObject, int level, Node p, ArrayList<Node>topList){
        Iterator<?> iterator1 = JSONObject.iterator();
        ArrayList <Node> childList = null;
        while (iterator1.hasNext()) {
        	JSONObject jsonObject = (JSONObject)iterator1.next();
            String title = (String) jsonObject.get("title");
            
            if(title.charAt(0)=='_'){
            	continue;
            }
            //System.out.println(level+"   "+ title);
            Node n = new Node();
            if (level==1){
            	topList.add(n);
            	n.parent = p;
            	n.title = title;
            	
           	
            	//g.toplist.add(n);
            }else{
            	n.parent = p;
            	p.children.add(n);
            	n.title = title;
            	
            }
            //System.out.println("Parent: "+p.title+" at level "+level+"Child: "+n.title);
            JSONArray children = (JSONArray) jsonObject.get("children");
            
           
            if(children!=null)
            {
            	//if(jsonObject.get("children") instanceof JSONObject){
            	if(n.children==null){
            		childList= new ArrayList<Node>() ;
                	n.children = childList;

            	}
            	
	            //System.out.println("\nchildren:");
	            readJSON(children,level+1,n,childList);

            	//}
            
           }else{
        	   //--level;
        	   continue;
           }
           
           
        }

	}
	public void displayGraph(Node p){
		if(p.parent!=null){
			System.out.println("parent "+p.parent.title+" Child: "+p.title);

		}
		if(p.children==null){
			return;
		}else{
			ArrayList<Node> child = p.children;
			for(int i=0;i< child.size();i++){
				Node n = child.get(i);
				displayGraph(n);
			}
		
		}
		
	}
	
	public static void main(String [] args){
		ReadKnowledgeBase RObj = new ReadKnowledgeBase();
		String owlFile = "C:/Sheetal/Research/ISecure/iSecure/WebContent/Ont.json";
		RObj.readOwlFile(owlFile);
		
		
	}

}
