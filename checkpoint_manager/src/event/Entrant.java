package event;

public class Entrant {
  
  private int id;
  private Course course;
  private String name;

	private EntrantStatus status;
  
  public Entrant(int id, Course course, String name) {
    setId(id);
    setCourse(course);
    setName(name);

		setStatus(EntrantStatus.NOT_STARTED);
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

	public EntrantStatus getStatus() {
		return status;
	}

	public void setStatus(EntrantStatus status) {
		this.status = status;
	}

}
