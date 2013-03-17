package event;

import java.util.ArrayList;

public class CourseList {

  private ArrayList<Course> courses;
  
  public CourseList() {
    courses = new ArrayList<Course>();
  }
  
  public void add(Course c) {
    courses.add(c);
  }
  
}
