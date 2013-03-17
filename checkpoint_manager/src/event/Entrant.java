package event;

public class Entrant {
  
  private int id;
  private Course course;
  private String name;
  
  public Entrant(int id, Course course, String name) {
    setId(id);
    setCourse(course);
    setName(name);
  }
  
  public int getId() {
    return id;
  }
  
  private void setId(int id) {
    this.id = id;
  }
  
  public Course getCourse() {
    return course;
  }
  
  private void setCourse(Course course) {
    this.course = course;
  }
  
  public String getName() {
    return name;
  }
  
  private void setName(String name) {
    this.name = name;
  }

}
