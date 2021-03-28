package courseDependency;
import java.util.ArrayList;
import java.util.Map;


public class Graph {
	Course root;
	Map<String,ArrayList<String>>courseAdjacencyList  = null;
	
	public Graph(){
		root  = new Course();
		root.courseCode = "root";
		root.preRequisite = null;
		
	}
	
	public void addCourse(String n,String preReq){
		Course newCourse = new Course();
		
		
		if(preReq==null){
			ArrayList<Course>preReqList = new ArrayList<>();
			preReqList.add(root);
			newCourse.preRequisite = preReqList;
			if(root.children==null){
				ArrayList<Course>childList = new ArrayList<>();
				childList.add(newCourse);
				root.children = childList;
			}else{
				ArrayList<Course> childList = root.children;
				childList.add(newCourse);
			}
			return;
		}else{
			Course parent = new Course();
			if(newCourse.preRequisite==null){
				newCourse.preRequisite = new ArrayList<>();
				parent.courseCode = preReq;
				
				newCourse.preRequisite.add(parent);
				
			}else{
				parent.courseCode = preReq;
				newCourse.preRequisite.add(parent);
	
			}
				parent.children = new ArrayList<Course>();
				parent.children.add(newCourse);
		}
		
	}
	
	
	public void getCoursePrereq(Course Course){
		// Return all the concepts get subtree under the Course
		int i=0;
	}
	public void searchEntireGraph(Course c){
		//bfs
		if(c==null){
			return;
		}
		c.visited = true;
		System.out.println(c.courseCode);
		ArrayList<Course> courseList = c.children;
		if(courseList!=null){
			for(Course course :courseList){
				if(course.visited==false){
					searchEntireGraph(course);
				}
			}
			
		}
		
	}
	public void match(Graph g1, Graph g2){
		//Match the two graphs
		
	}
	

}
