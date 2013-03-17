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
  
  public Event(String name, Calendar date) {
    setName(name);
    setDate(date);
    
    df = new SimpleDateFormat("dddd-MMMM-yyyy");
    
    nodes = new ArrayList<Node>();
    tracks = new ArrayList<Track>();
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
  
  public Track getTrack(char id) {
    for (Track t : tracks) {
      if (t.getId() == id) return t;
    }
    return null;
  }
  
  public void addTrack(Track t) {
    tracks.add(t);
  }
  
}