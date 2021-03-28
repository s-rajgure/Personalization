package courseDependency;

import java.util.ArrayList;

public class Course {
	String courseCode;
	String courseName;
	ArrayList<Course>preRequisite;
	ArrayList<Course>children;
	boolean visited;
	@Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Course or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Course)) {
            return false;
        }
         
        // typecast o to Course so that we can compare data members 
        Course c = (Course) o;
         
        // Compare the data members and return accordingly 
        return courseCode.equals(c.courseCode);
    }

}
