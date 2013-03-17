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

  public Course getCourseById(char id) {
    for (Course c : courses) {
      if (c.getId() == id) return c;
    }
    return null;
  }
  
}
