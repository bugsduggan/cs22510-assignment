package event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Event {

  private String name;
  private Calendar date;
  private DateFormat df;
  
  private List<Node> nodes;
  private List<Track> tracks;
  private List<Course> courses;
  private List<Entrant> entrants;
  
  public Event(String name, Calendar date) {
    setName(name);
    setDate(date);
    
    df = new SimpleDateFormat("dddd-MMMM-yyyy");
    
    nodes = new ArrayList<Node>();
    tracks = new ArrayList<Track>();
    courses = new ArrayList<Course>();
    entrants = new ArrayList<Entrant>();
  }
  
  public String getName() {
    return name;
  }
  
  private void setName(String name) {
    this.name = name;
  }
  
  public String getDateString() {
    return df.format(date.getTime());
  }
  
  public String getTimeString() {
    return date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE);
  }
  
  private void setDate(Calendar date) {
    this.date = date;
  }

  public Node getNode(int id) {
    for (Node n : nodes) {
      if (n.getId() == id) return n;
    }
    return null;
  }
  
  public void addNode(Node n) {
    nodes.add(n);
  }
  
  public void addNodes(List<Node> nodes) {
    this.nodes.addAll(nodes);
  }
  
  public Track getTrack(int id) {
    for (Track t : tracks) {
      if (t.getId() == id) return t;
    }
    return null;
  }
  
  public Track getTrack(Node start, Node end) {
    for (Track t : tracks) {
      if (t.getStartNode() == start && t.getEndNode() == end ||
          t.getStartNode() == end && t.getEndNode() == start)
        return t;
    }
    return null;
  }
  
  public void addTrack(Track t) {
    tracks.add(t);
  }
  
  public void addTracks(List<Track> tracks) {
    this.tracks.addAll(tracks);
  }
  
  public Course getCourse(char id) {
    for (Course c : courses) {
      if (c.getId() == id) return c;
    }
    return null;
  }
  
  public void addCourse(Course c) {
    courses.add(c);
  }
  
  public void addCourses(List<Course> courses) {
    this.courses.addAll(courses);
  }
  
  public Entrant getEntrant(int id) {
    for (Entrant e : entrants) {
      if (e.getId() == id) return e;
    }
    return null;
  }
  
  public void addEntrant(Entrant e) {
    entrants.add(e);
  }
  
  public void addEntrants(List<Entrant> entrants) {
    this.entrants.addAll(entrants);
  }

  public void applyUpdate(UpdateEvent evt) {
    switch (evt.getType()) {
      case 'T':
        // TODO
        break;
      case 'A':
        // TODO
        break;
      case 'D':
        // TODO
        break;
      case 'I':
        // TODO
        break;
      case 'E':
        // TODO
        break;
      default:
        System.err.println("Failed to parse update - possible data loss");
        break;
    }
  }
  
}