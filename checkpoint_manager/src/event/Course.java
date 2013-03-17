package event;

public class Course {

  private char id;
  
  public Course(char id) {
    setId(id);
  }
  
  public char getId() {
    return id;
  }
  
  private void setId(char id) {
    this.id = id;
  }
  
}
