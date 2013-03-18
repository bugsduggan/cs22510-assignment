package event;

public class Entrant {
  
  private int id;
  private Course course;
  private String name;

	private EntrantStatus status;
	private Node lastNode;
	private Track currentTrack;
  
  public Entrant(int id, Course course, String name) {
    setId(id);
    setCourse(course);
    setName(name);

		setStatus(EntrantStatus.NOT_STARTED);
		setLastNode(null);
		setCurrentTrack(null);
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

	public Node getLastNode() {
		return lastNode;
	}

	private void setLastNode(Node node) {
		lastNode = node;
	}

	public Track getCurrentTrack() {
		return currentTrack;
	}

	private void setCurrentTrack(Track track) {
		currentTrack = track;
	}

	public void update(Time time) {
		if (getStatus() == EntrantStatus.RUNNING) {
			// TODO
		}
	}

}
